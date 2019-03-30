package org.gravitechx.frc2019.utils.autoutilities;

import edu.wpi.first.wpilibj.Timer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.gravitechx.frc2019.robot.subsystems.badiosubsystem.Arm;

public class AutoCSVReader {
	private final double stallCurrentThreshold = 1.4;//NEED TO FINE TUNE WITH EXPERIMENTAL VALUES
    private final double stallTickSpeed = 75.0;//Ticks per 100 miliseconds. There are 4096 ticks in a wheel rotation
    private final double stallWaitTime = 0.5;//In seconds
    private boolean waitForStall = false;
    private BufferedReader br;
    private double[] latestSetpoints, nextSetpoints;
    private double initialTime, initialStallTime, pauseForArmMovement;
    private Arm arm;
    arm = Arm.getArmInstance();
    
    public AutoCSVReader() throws FileNotFoundException, IOException{
        resetReader();
    }
    
    public double[] getSetpoints(double leftTickSpeed, double rightTickSpeed, double[] pdpCurrents) throws IOException{
        String line;
        if(initialTime == 0) {
            initialTime = Timer.getFPGATimestamp();
        }
        if(!Arrays.equals(nextSetpoints,new double[]{})) {//If there's a possibility that lastestSetpoints needs to be updated
            if(waitForStall) {//If we're waiting for the robot to hit a wall and stall out the motors
            	double leftPDPCurrent = (pdpCurrents[0] + pdpCurrents[1] + pdpCurrents[2])/3.0;
            	double rightPDPCurrent = (pdpCurrents[3] + pdpCurrents[4] + pdpCurrents[5])/3.0;
                if(Math.abs(leftTickSpeed) < stallTickSpeed && Math.abs(rightTickSpeed) < stallTickSpeed && leftPDPCurrent > stallCurrentThreshold && rightPDPCurrent > stallCurrentThreshold) {
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
	        	while(Timer.getFPGATimestamp()-initialTime >= nextSetpoints[0] && waitForStall == false && Timer.getFPGATimestamp()-pauseForArmMovement >= 0) {//If it's time to start using nextSetpoints
	                latestSetpoints = nextSetpoints;
	                if((line = br.readLine()) != null) {//If there's any more lines to read
	                	if(!line.substring(0,1).equals("!") && !line.substring(0,1).equals("@")) {//If line != '!!!!!UntilStall!!!!!' or '@ArmAction@'
	                		nextSetpoints = stringsToDoubles(line.split(","));
                        } else if(line.substring(0,1).equals("!")){//If line == '!!!!!UntilStall!!!!!'
	                		waitForStall = true;
	                		line = br.readLine();//Now line == '-'
	                		line = br.readLine();//Now line is set to the line of setpoints to keep repeating until the motors stall
                            latestSetpoints = stringsToDoubles(line.split(","));
                            nextSetpoints = new double[]{0,0,0,0,0};
	                		line = br.readLine();//Now line == '-'
	                	} else if(line.substring(0,1).equals("@")) {
	                		
                            switch(line.substring(0,21)) {
                                case "@arm.setAutonVacuumPo" :
                                    switch(line.substring(28,line.length()-1)) {
                                        case "VacuumPosition.DOWN" :
                                            arm.setAutonVacuumPosition(VacuumPosition.DOWN);
                                        default :
                                            System.out.println("@arm.setAutonVacuumPosition is having issues reading what is inisde the parentheses in its call in the setpoints file.");
                                    }
                                    pauseForArmMovement = Timer.getFPGATimestamp() + 3;
                                case "@arm.setAutonPosition" :
                                    switch(line.substring(22,line.length()-1)) {
                                        case "ButtonArmPosition.CARGOBAY" :
                                            arm.setAutonPosition(ButtonArmPosition.CARGOBAY);
                                        default :
                                            System.out.println("@arm.setAutonPosition is having issues reading what is inisde the parentheses in its call in the setpoints file.");
                                    }
                                    pauseForArmMovement = Timer.getFPGATimestamp() + 3;
                                case "@arm.setAutonIntakeSt" :
                                    switch(line.substring(25,line.length()-1)) {
                                        case "IntakeState.EXHALE" :
                                            arm.setAutonIntakeState(IntakeState.EXHALE);
                                        case "IntakeState.NEUTRAL" :
                                            arm.setAutonIntakeState(IntakeState.NEUTRAL);
                                        default :
                                            System.out.println("@arm.setAutonIntakeState is having issues reading what is inisde the parentheses in its call in the setpoints file.");
                                    }
                                    pauseForArmMovement = Timer.getFPGATimestamp() + 3;
                                default :
                                    System.out.println("Can't read the command given in the setpoints file. The line '" + line + "' is unreadable.");
                            }
                            
                        }
	                } else {
                        nextSetpoints = new double[]{};
	                }
	            }
            }
        }
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
        pauseForArmMovement = Timer.getFPGATimestamp();
    }
}