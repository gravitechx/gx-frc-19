package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path {

    private List<double[]> prePath = new ArrayList<double[]>();
    public static final double MAX_ACCELERATION_OF_ROBOT = 3.0;
    public static final double MAX_VELOCITY_OF_ROBOT = 12.0 ;
    public static final double FREQUENCY = 1.0/200.0;
    public int index = 0;
    public List<double[]> leftRightMotor = new ArrayList<>(); // format: [time, lVel, lAcc, rVel, rAcc]

    public Path(){
        leftRightMotor.add(new double[]{0,0,0,0,0}); //use constructor to create new double[]
    }

    public void addCurve(double distance, double radians){
        prePath.add(new double[]{1,distance, radians}); //add maximum velocity
    }

    public void addLine(double distance){
        prePath.add(new double[]{0, MAX_VELOCITY_OF_ROBOT, distance});
    }
    
    public void generatePath(){
        
        prePath.add(new double[]{0, 0, 0});//tell robot to decelerate to zero (position at rest)

        double rCurrentVel = 0.0;
        double lCurrentVel = 0.0;
        double currentT = 0.0;

        for(int i = 0; i < prePath.size(); i++){
            if(prePath.get(i)[0] == 0) //if path is line
            {
                boolean decelerate = false;
                double currentDistance = 0.0;
                for (int j = leftRightMotor.size() - 1; currentDistance < prePath.get(i)[2]; j++){
                    currentT += FREQUENCY;
                    currentDistance += FREQUENCY * leftRightMotor.get(j)[1]; //update distance covered
                    try {
                        if(decelerate){
                            lCurrentVel -= FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //decrease velocity
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, -MAX_ACCELERATION_OF_ROBOT, lCurrentVel, -MAX_ACCELERATION_OF_ROBOT}); //add new setpoint
                        }else if(prePath.get(i)[2] - currentDistance > 0 && currentDistance + (lCurrentVel * Math.abs(lCurrentVel - prePath.get(i+1)[1]) / MAX_ACCELERATION_OF_ROBOT) - (Math.pow(lCurrentVel - prePath.get(i+1)[1], 2) / (2*MAX_ACCELERATION_OF_ROBOT)) >= prePath.get(i)[2]){ //if still distance left to cover and it's time to start decelerating
                            decelerate = true;
                            lCurrentVel -= FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //decrease velocity
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, -MAX_ACCELERATION_OF_ROBOT, lCurrentVel, -MAX_ACCELERATION_OF_ROBOT}); //add new setpoint
                        }else if(MAX_VELOCITY_OF_ROBOT <= lCurrentVel){
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, 0, lCurrentVel, 0}); //add new setpoint
                        }else{
                            if(lCurrentVel + FREQUENCY * MAX_ACCELERATION_OF_ROBOT > MAX_VELOCITY_OF_ROBOT){//if next step results in current velocity greater than max velocity
                                lCurrentVel = MAX_VELOCITY_OF_ROBOT;
                            }else{
                                lCurrentVel += FREQUENCY * MAX_ACCELERATION_OF_ROBOT;     //increase velocity normally
                            }
                            leftRightMotor.add(new double[]{currentT, lCurrentVel, MAX_ACCELERATION_OF_ROBOT, lCurrentVel, MAX_ACCELERATION_OF_ROBOT}); //add new setpoint
                        }
                    } catch (OutOfMemoryError e) {
                        System.out.println(leftRightMotor.size());
                        System.exit(1);
                    }
                }
            }
        }
    }
    
    public double[] update(){
        if(index >= leftRightMotor.size()){ //if no more setpoints to send
            return new double[]{0,0,0,0};
        }
        return Arrays.copyOfRange(leftRightMotor.get(index++), 1, 5); //Receiving mini array and updating index
    }
}