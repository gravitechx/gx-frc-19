package app;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.io.*;



public class Path {



    private List<double[]> prePath = new ArrayList<double[]>();

    private static final double MAX_ACCELERATION_OF_ROBOT = 3.0;

    private static final double MAX_VELOCITY_OF_ROBOT = 12.0 ;

    private static final double FREQUENCY = 1.0/200.0;

    private int index = 0;

    private List<double[]> leftRightMotor = new ArrayList<double[]>(); // format: [time, lVel, lAcc, rVel, rAcc]



    public Path(){

        leftRightMotor.add(new double[]{0,0,0,0,0}); //use constructor to create new double[]

    }



    public void addCurve(double distance, double radians){

        prePath.add(new double[]{1,distance, radians}); //add maximum velocity

    }



    public void addLine(double distance){

        prePath.add(new double[]{0, MAX_VELOCITY_OF_ROBOT, distance});

    }

    

    public void generatePath() throws InterruptedException, IOException{

        

    	FileWriter fileWriter = new FileWriter("C:/Users/FRC/Desktop/temp/setpoint.txt");	//create a text file to store setpoints

    	PrintWriter printWriter = new PrintWriter(fileWriter);

        prePath.add(new double[]{0, 0, 0});//tell robot to decelerate to zero (position at rest)


        double rCurrentVel = 0.0;

        double lCurrentVel = 0.0;

        double currentT = 0.0;



        for(int i = 0; i < prePath.size(); i++){

            if(prePath.get(i)[0] == 0.0){ //if path is line
                double kAcceleration = MAX_ACCELERATION_OF_ROBOT * FREQUENCY / 2.0;
                if(leftRightMotor.size() == 0){//if there are no setpoints in path, then create initial setpoint
                    leftRightMotor.add(new double[]{0, kAcceleration, MAX_ACCELERATION_OF_ROBOT, kAcceleration, MAX_ACCELERATION_OF_ROBOT});
                }
                boolean decelerate = false;

                double currentDistance = 0.0;

                for (int j = leftRightMotor.size() - 1; checkDistance(currentDistance, prePath.get(i)[2], leftRightMotor.get(j)[1]) < prePath.get(i)[2]; j++){

                	Thread.sleep(10);

                    currentT += FREQUENCY;

                    currentDistance += FREQUENCY * leftRightMotor.get(j)[1]; //update distance covered

                    if(decelerate){

                        lCurrentVel -= FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //decrease velocity

                        leftRightMotor.add(new double[]{currentT, subtractVel(lCurrentVel, kAcceleration), -MAX_ACCELERATION_OF_ROBOT, subtractVel(lCurrentVel, kAcceleration), -MAX_ACCELERATION_OF_ROBOT}); //add new setpoint

                    }else if(prePath.get(i)[2] - currentDistance > 0 && currentDistance + (lCurrentVel * Math.abs(lCurrentVel - prePath.get(i+1)[1]) / MAX_ACCELERATION_OF_ROBOT) - (Math.pow(lCurrentVel - prePath.get(i+1)[1], 2) / (2*MAX_ACCELERATION_OF_ROBOT)) >= prePath.get(i)[2]){ //if still distance left to cover and it's time to start decelerating

                        decelerate = true;

                        lCurrentVel -= FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //decrease velocity

                        leftRightMotor.add(new double[]{currentT, subtractVel(lCurrentVel, kAcceleration), -MAX_ACCELERATION_OF_ROBOT, subtractVel(lCurrentVel, kAcceleration), -MAX_ACCELERATION_OF_ROBOT}); //add new setpoint

                    }else if(prePath.get(i)[1] <= lCurrentVel){

                        leftRightMotor.add(new double[]{currentT, lCurrentVel, 0, lCurrentVel, 0}); //add new setpoint

                    }else{

                        if(lCurrentVel + FREQUENCY * MAX_ACCELERATION_OF_ROBOT > prePath.get(i)[1]){//if next step results in current velocity greater than max velocity

                            lCurrentVel = prePath.get(i)[1];
                        }else{
                            lCurrentVel += FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //increase velocity normally

                        }

                        leftRightMotor.add(new double[]{currentT, addVel(lCurrentVel, kAcceleration, prePath.get(i)[1]), MAX_ACCELERATION_OF_ROBOT, addVel(lCurrentVel, kAcceleration, prePath.get(i)[1]), MAX_ACCELERATION_OF_ROBOT}); //add new setpoint

                    }

                    System.out.println("Time: "+ leftRightMotor.get(j)[0] + ", lVel: " + leftRightMotor.get(j)[1] + ", lAccel: " + leftRightMotor.get(j)[2] + ", rVel: " + leftRightMotor.get(j)[3] + ", rAccel: "+ leftRightMotor.get(j)[4] +", Distance covered: "+ currentDistance); //print out setpoint to test errors

                    printWriter.printf("%f,%f%n", leftRightMotor.get(j)[0], leftRightMotor.get(j)[1]);	//add a setpoint to the text file

                }

            }

            if(prePath.get(i)[0] == 1.0){

            	

            }

            

        }

        printWriter.close();

    }

    

    public double[] update(){

        if(index >= leftRightMotor.size()){ //if no more setpoints to send

            return new double[]{0,0,0,0};

        }

        return Arrays.copyOfRange(leftRightMotor.get(index++), 1, 5); //Receiving mini array and updating index

    }

    private double subtractVel(double currentVel, double deltaVel){
        if(currentVel - deltaVel < 0.0){
            return 0.0;
        }
        return currentVel - deltaVel;
    }

    private double addVel(double currentVel, double deltaVel, double maxVel){
        if(currentVel + deltaVel > maxVel){
            return maxVel;
        }
        return currentVel + deltaVel;
    }

    private double checkDistance(double currentDistance, double finalDistance, double currentVel){
        if(currentDistance + FREQUENCY * currentVel > finalDistance){
            //System.out.println("TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE ");
            return finalDistance;
        }
        return currentDistance;
    }
}