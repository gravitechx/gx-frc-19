import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Path {

    private static final double DISTANCE_BETWEEN_WHEELS = 0.75; //meters
    private List<double[]> prePath = new ArrayList<double[]>();
    private static final double MAX_ACCELERATION_OF_ROBOT = 2.0;
    private static final double MAX_VELOCITY_OF_ROBOT = 3.0 ;
    private static final double FREQUENCY = 0.005;
    private int index = 0;
    private List<double[]> leftRightMotor = new ArrayList<double[]>(); // format: [time, lVel, lAcc, rVel, rAcc]

    public Path(){
        leftRightMotor.add(new double[]{0,0,0,0,0}); //use constructor to create new double[]
    }

    public void addCurve(double radius, double radians, double maxVelocity){
        //prePath.add(new double[]{1, maxVelocity, Math.abs(radians * (radius + DISTANCE_BETWEEN_WHEELS / 2.0)), radius, radians}); //add maximum velocity. Negative radians means robot turns left
        prePath.add(new double[]{1, maxVelocity, Math.abs(radians * radius), radius, radians}); //add maximum velocity. Negative radians means robot turns left
    }

    public void addLine(double distance){
        prePath.add(new double[]{0, MAX_VELOCITY_OF_ROBOT, distance});
    }  

    public void generatePath() throws InterruptedException, IOException{

    	FileWriter fileWriter = new FileWriter("C://Users//Nathan//Desktop//setpoint//straightTrajectory.txt");	//create a text file to store setpoints
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        prePath.add(new double[]{0, 0, 0});//tell robot to decelerate to zero (position at rest)
        
        double rCurrentVel = 0.0;
        double lCurrentVel = 0.0;
        double currentT = 0.0;

        for(int i = 0; i < prePath.size(); i++){
            if(prePath.get(i)[0] == 0.0){ //if current prePath segment is line
                boolean decelerate = false;
                double currentDistance = 0.0;
                double customDeceleration = 0.0;
                //System.out.println(prePath.get(i)[2]-currentDistance);
                for (int j = leftRightMotor.size() - 1; prePath.get(i)[2]-currentDistance > 0.00001; j++){ //While distance for current prePath segment hasn't been covered (0.00001 margin of error)
                    //Thread.sleep(10);
                    //System.out.println("true");
                    currentT += FREQUENCY;
                    lCurrentVel += (leftRightMotor.get(j)[2] * FREQUENCY);
                    currentDistance += FREQUENCY * (leftRightMotor.get(j)[1] + lCurrentVel) / 2.0; //update distance covered

                    if(decelerate){ //If decelerating
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, customDeceleration, lCurrentVel, customDeceleration});
                    }else if(prePath.get(i)[2] - currentDistance > 0 && currentDistance + (lCurrentVel * Math.abs(lCurrentVel - prePath.get(i+1)[1]) / MAX_ACCELERATION_OF_ROBOT) - (Math.pow(lCurrentVel - prePath.get(i+1)[1], 2) / (2*MAX_ACCELERATION_OF_ROBOT)) + FREQUENCY * (lCurrentVel + leftRightMotor.get(j)[2] * FREQUENCY / 2.0) >= prePath.get(i)[2]){ //if still distance left to cover and it's time to start decelerating
                        decelerate = true;
                        customDeceleration = -Math.pow(lCurrentVel - prePath.get(i+1)[1],2) / (2*(prePath.get(i)[2] - currentDistance)); //Custom Deceleration value: Math.pow(CurrentVel - FinalVel,2)/(2*DistanceLeft)
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, customDeceleration, lCurrentVel, customDeceleration});
                    }else if(prePath.get(i)[1] <= lCurrentVel){ //If already at Max Velocity for this prePath segment
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, 0, lCurrentVel, 0});
                    }else{ //Else we are accelerating
                        if(lCurrentVel + MAX_ACCELERATION_OF_ROBOT * FREQUENCY <= MAX_VELOCITY_OF_ROBOT){ //Normal acceleration setpoint
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, MAX_ACCELERATION_OF_ROBOT, lCurrentVel, MAX_ACCELERATION_OF_ROBOT});
                        }else{ //If the next step makes a setpoint above the Max Velocity
                            double cAccel = (MAX_VELOCITY_OF_ROBOT - lCurrentVel) / FREQUENCY;
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, cAccel, lCurrentVel, cAccel});
                        }
                    }
                    //System.out.println("Time: "+ leftRightMotor.get(j+1)[0] + ", lCurrentVel: " + lCurrentVel +", lAccel: " + leftRightMotor.get(j+1)[2]  + ", rAccel: "+ leftRightMotor.get(j+1)[4] +", Distance covered: "+ currentDistance); //print out setpoint to test errors
                    printWriter.println(leftRightMotor.get(j)[0] + "," + leftRightMotor.get(j)[1] + "," + leftRightMotor.get(j)[2] + "," + leftRightMotor.get(j)[3] + "," +  leftRightMotor.get(j)[4]);	//add a setpoint to the text file
                }
            }
            if(prePath.get(i)[0] == 1.0){
                double kConv = ((prePath.get(i)[3] - DISTANCE_BETWEEN_WHEELS / 2.0)/(prePath.get(i)[3] + DISTANCE_BETWEEN_WHEELS / 2.0));
                double rMaxVel;
                double lMaxVel;
                double rAccel;
                double lAccel;
                boolean decelerate = false;
                double lCurrentDistance = 0.0;
                double rCurrentDistance = 0.0;
                double currentDistance = 0.0;
                double lCustomDeceleration = 0.0;
                double rCustomDeceleration = 0.0;
                double rFinalDistance = 0.0;
                double lFinalDistance = 0.0;
                double currentVel = 0.0;
                if(prePath.get(i)[4] < 0.0){  //if robot turns left, max outer acceleration is assigned to right wheel
                    rAccel = MAX_ACCELERATION_OF_ROBOT;
                    lAccel = MAX_ACCELERATION_OF_ROBOT * kConv;
                    rMaxVel = prePath.get(i)[1];
                    lMaxVel = prePath.get(i)[1] * kConv;
                    rFinalDistance = (prePath.get(i)[3] + DISTANCE_BETWEEN_WHEELS / 2.0) * prePath.get(i)[4];
                    lFinalDistance = (prePath.get(i)[3] - DISTANCE_BETWEEN_WHEELS / 2.0) * prePath.get(i)[4];
                }else{  //if robot turns right, max outer acceleration is assigned to left wheel
                    lAccel = MAX_ACCELERATION_OF_ROBOT;
                    rAccel = MAX_ACCELERATION_OF_ROBOT * kConv;
                    lMaxVel = prePath.get(i)[1];
                    rMaxVel = prePath.get(i)[1] * kConv;
                    rFinalDistance = (prePath.get(i)[3] - DISTANCE_BETWEEN_WHEELS / 2.0) * prePath.get(i)[4];
                    lFinalDistance = (prePath.get(i)[3] + DISTANCE_BETWEEN_WHEELS / 2.0) * prePath.get(i)[4];
                }
                
                for (int j = leftRightMotor.size() - 1; prePath.get(i)[2] - currentDistance > 0.00001; j++){ //While distance for current prePath segment hasn't been covered (0.00001 margin of error)
                    //Thread.sleep(10);

                    currentT += FREQUENCY;
                    lCurrentVel += leftRightMotor.get(j)[2] * FREQUENCY;
                    rCurrentVel += leftRightMotor.get(j)[4] * FREQUENCY;
                    currentVel += (leftRightMotor.get(j)[2] + leftRightMotor.get(j)[4])/2.0 *FREQUENCY;
                    lCurrentDistance += FREQUENCY * (leftRightMotor.get(j)[1] + lCurrentVel) / 2.0; //update distance covered
                    rCurrentDistance += FREQUENCY * (leftRightMotor.get(j)[3] + rCurrentVel) / 2.0;
                    currentDistance += FREQUENCY * (((leftRightMotor.get(j)[3] + leftRightMotor.get(j)[1]) / 2.0) + currentVel)/ 2.0;
                    System.out.println(lFinalDistance);
                    if(decelerate){ //If decelerating
                        System.out.println("Decelerating....");
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, lCustomDeceleration, rCurrentVel, rCustomDeceleration});
                    }else if(prePath.get(i)[2] - currentDistance > 0 && currentDistance + (currentVel * Math.abs(currentVel - prePath.get(i+1)[1]) / ((prePath.get(i)[3] * MAX_ACCELERATION_OF_ROBOT)/(prePath.get(i)[3] + DISTANCE_BETWEEN_WHEELS / 2.0))) - (Math.pow(currentVel - prePath.get(i+1)[1], 2) / (2*((prePath.get(i)[3] * MAX_ACCELERATION_OF_ROBOT)/(prePath.get(i)[3] + DISTANCE_BETWEEN_WHEELS / 2.0)))) + FREQUENCY * (currentVel + (leftRightMotor.get(j)[2] + leftRightMotor.get(j)[4]) / 2.0 * FREQUENCY / 2.0) >= prePath.get(i)[2]){ //if still distance left to cover and it's time to start decelerating
                        decelerate = true;
                        System.out.println("Now Decelerating....");
                        //-Math.pow(lCurrentVel - prePath.get(i+1)[1],2) / (2*(prePath.get(i)[2] - currentDistance))
                        System.out.println(-Math.pow(lCurrentVel - prePath.get(i+1)[1],2) / (2*(lFinalDistance - lCurrentDistance)));
                        lCustomDeceleration = -Math.pow(lCurrentVel - prePath.get(i+1)[1],2) / (2*(lFinalDistance - lCurrentDistance)); //Custom Deceleration value: Math.pow(CurrentVel - FinalVel,2)/(2*DistanceLeft)
                        rCustomDeceleration = -Math.pow(rCurrentVel - prePath.get(i+1)[1],2) / (2*(rFinalDistance - rCurrentDistance));
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, lCustomDeceleration, rCurrentVel, rCustomDeceleration});
                    }else if(currentVel >= prePath.get(i)[1]){ //If already at Max Velocity for this prePath segment
                        leftRightMotor.add(new double[]{currentT, lCurrentVel, 0, rCurrentVel, 0});
                    }else{ //Else we are accelerating
                        System.out.println("Accelerating...");
                        if(currentVel + (lAccel + rAccel)/2.0 * FREQUENCY <= prePath.get(i)[1]){ //Normal acceleration setpoint
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, lAccel, rCurrentVel, rAccel});
                        }else{ //If the next step makes a setpoint above the Max Velocity
                            double clAccel = (lMaxVel - lCurrentVel) / FREQUENCY;
                            double crAccel = (rMaxVel - rCurrentVel) / FREQUENCY;
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, clAccel, rCurrentVel, crAccel});
                        }
                    }
                    //System.out.println("Time: "+ currentT + "\nlCurrentVel: " + lCurrentVel +"\nlAccel: " + leftRightMotor.get(j+1)[2]  + "\nrCurrentVel: " + lCurrentVel + "\nrAccel: "+ leftRightMotor.get(j+1)[4] +"\nDistance covered: "+ currentDistance + "\nrCurrentDistance: " + rCurrentDistance + "\nlCurrentDistance: " + lCurrentDistance +"\n"); //print out setpoint to test errors
                    printWriter.printf("%f,%f%n", leftRightMotor.get(j)[0], leftRightMotor.get(j)[1]);	//add a setpoint to the text file
                    printWriter.printf("%f,%f%n", leftRightMotor.get(j)[0], leftRightMotor.get(j)[3]);	//add a setpoint to the text file
                }
                

            }
            printWriter.println(currentT + ",0.00000,0.00000,0.00000,0.00000");
            printWriter.println("!!!!!UntilStall!!!!!");
            printWriter.println("-");
            printWriter.println("0.00000,-0.25000,0.00000,-0.25000,0.00000");
            printWriter.println("-");
            printWriter.close();
        }
        leftRightMotor.add(new double[]{currentT+FREQUENCY,0,0,0,0});
    }

    public double[] update(){

        if(index >= leftRightMotor.size()){ //if no more setpoints to send
            return new double[]{0,0,0,0};
        }
        return Arrays.copyOfRange(leftRightMotor.get(index++), 1, 5); //Receiving mini array and updating index
    }

    public void writeToFile(String targetDir) throws InterruptedException, IOException{
        FileWriter fileWriter = new FileWriter(targetDir + "AutoSetpoints.txt");	//create a text file to store setpoints
        PrintWriter printWriter = new PrintWriter(fileWriter);

        if(!targetDir.substring(targetDir.length()-1,targetDir.length()).equals("\\")) {//Makes sure there is a slash already at the end of the path
            targetDir += "\\";
        }
        printWriter.println("Time,Left Velocity,Left Acceleration,Right Velocity,Right Acceleration");
        for(int i = 0;i<leftRightMotor.size();i++) {
            printWriter.printf("%f,%f,%f,%f,%f%n", leftRightMotor.get(i)[0], leftRightMotor.get(i)[1],leftRightMotor.get(i)[2],leftRightMotor.get(i)[3],leftRightMotor.get(i)[4]);	//add a setpoint to the text file
        }
        printWriter.close();
        System.out.println(targetDir + "AutoSetpoints.txt has been written!");
    }

    public String toString() {
        String result = "prePath: {";
        for (double[] preElements : prePath) {
            result += Arrays.toString(preElements) + ",";
        }
        result += "}";
        return result;
    }
}