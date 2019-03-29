package org.gravitechx.frc2019.utils.autoutilities;

import edu.wpi.first.wpilibj.Timer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AutoCSVReader {
	private final double stallCurrentThreshold = 1.4;//NEED TO FINE TUNE WITH EXPERIMENTAL VALUES
    private final double stallTickSpeed = 75.0;//Ticks per 100 miliseconds. There are 4096 ticks in a wheel rotation
    private final double stallWaitTime = 0.5;//In seconds
    private boolean waitForStall = false;
    private BufferedReader br;
    private double[] latestSetpoints, nextSetpoints;
    private double initialTime, initialStallTime;
    public AutoCSVReader() throws FileNotFoundException, IOException{
        resetReader();
    }
    public double[] getSetpoints(double leftTickSpeed, double rightTickSpeed, double[] pdpCurrents) throws IOException{
        String line;
        if(initialTime == 0) {
            initialTime = Timer.getFPGATimestamp();
        }
        System.out.println("NextSetpoints at top: " + !Arrays.equals(nextSetpoints,new double[]{}));
        if(!Arrays.equals(nextSetpoints,new double[]{})) {//If there's a possibility that lastestSetpoints needs to be updated
            System.out.println("Waitforstall: " + waitForStall);
            if(waitForStall) {//If we're waiting for the robot to hit a wall and stall out the motors
            	double leftPDPCurrent = (pdpCurrents[0] + pdpCurrents[1] + pdpCurrents[2])/3.0;
            	double rightPDPCurrent = (pdpCurrents[3] + pdpCurrents[4] + pdpCurrents[5])/3.0;
                System.out.println("Against Wall: " + (Math.abs(leftTickSpeed) < stallTickSpeed && Math.abs(rightTickSpeed) < stallTickSpeed && leftPDPCurrent > stallCurrentThreshold && rightPDPCurrent > stallCurrentThreshold));
                if(Math.abs(leftTickSpeed) < stallTickSpeed && Math.abs(rightTickSpeed) < stallTickSpeed && leftPDPCurrent > stallCurrentThreshold && rightPDPCurrent > stallCurrentThreshold) {
                    System.out.println(Timer.getFPGATimestamp()-initialStallTime);
                    if(initialStallTime == -1) {
                        initialStallTime = Timer.getFPGATimestamp();
                    } else if(Timer.getFPGATimestamp()-initialStallTime > stallWaitTime) {
                        waitForStall = false;
                        br = new BufferedReader(new FileReader("/home/admin/PathFive.txt"));
                        latestSetpoints = stringsToDoubles(br.readLine().split(","));
                        nextSetpoints = stringsToDoubles(br.readLine().split(","));
                        initialTime = Timer.getFPGATimestamp();
                    }
            	} else {
                    initialStallTime = -1;
                }
            } else {
	        	while(Timer.getFPGATimestamp()-initialTime >= nextSetpoints[0] && waitForStall == false) {//If it's time to start using nextSetpoints
	                latestSetpoints = nextSetpoints;
	                if((line = br.readLine()) != null) {//If there's any more lines to read
	                	if(!line.substring(0,1).equals("!") && !line.substring(0,1).equals("@")) {//If line != '!!!!!UntilStall!!!!!' or '@ArmAction@'
	                		nextSetpoints = stringsToDoubles(line.split(","));
                        } else if(line.substring(0,1).equals("!")){//If line == '!!!!!UntilStall!!!!!'
                            System.out.println("Reading !!!!!UntilStall!!!!! loop");
	                		waitForStall = true;
	                		line = br.readLine();//Now line == '-'
	                		line = br.readLine();//Now line is set to the line of setpoints to keep repeating until the motors stall
                            latestSetpoints = stringsToDoubles(line.split(","));
                            nextSetpoints = new double[]{0,0,0,0,0};
	                		line = br.readLine();//Now line == '-'
	                	}/*ARMACTIONS else if(line.substring(0,1).equals("@")) {
	                	    line = br.readLine();//Now line == ChangeArmPositionTo(...) <- No quotes inside of parentheses
                            switch(line.substring(20,line.length()-1)) {
                                case "CargoShip" :
                                    //Move arm to appropriate setposition
                                default :
                                    System.out.println("AutoCSVReader didn't understand the content in the 'ChangeArmPositionTo()' setpoint.");
                            }
                            line = br.readLine();//Now line == SetArmActionTo(...) <- No quotes inside of parentheses
                            switch(line.substring(15,line.length()-1)) {
                                case "Intake" :
                                    //Set rollers on arm to intake
                                case "Outtake" :
                                    //Set rollers on arm to outtake
                            }
                        }*/
	                } else {
                        System.out.println("RUNNING THIS THING!!! NextSetpoints Before:" + Arrays.toString(nextSetpoints));
                        nextSetpoints = new double[]{};
                        System.out.println("NextSetpoints After:" + Arrays.toString(nextSetpoints));
	                }
	            }
            }
        }
        //System.out.println("TIME: " + (Timer.getFPGATimestamp()-initialTime));
        return Arrays.copyOfRange(latestSetpoints, 1, 5);
    }
    private double[] stringsToDoubles(String[] strArray) {
        double[] result = new double[strArray.length];
        for(int i = 0;i<strArray.length;i++) {
            result[i] = Double.parseDouble(strArray[i]);
        }
        return result;
    }
    public void resetReader() throws FileNotFoundException, IOException{
        br = new BufferedReader(new FileReader("/home/admin/yeetCode.txt"));
        br.readLine();
        latestSetpoints = stringsToDoubles(br.readLine().split(","));
        nextSetpoints = stringsToDoubles(br.readLine().split(","));
        initialTime = 0;
        waitForStall = false;
        initialStallTime = -1;
    }
}