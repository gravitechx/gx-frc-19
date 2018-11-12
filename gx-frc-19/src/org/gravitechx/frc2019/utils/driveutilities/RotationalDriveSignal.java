package org.gravitechx.frc2019.utils.driveutilities;

import org.gravitechx.frc2019.robot.Constants;

public class RotationalDriveSignal {
	private double speedSignal, rotationalSignal;
	
	public RotationalDriveSignal(double givenSignal, double givenRotation){
		speedSignal = givenSignal;
		rotationalSignal = givenRotation;
	}
	
	public double getSpeed(){
		return speedSignal;
	}
	
	public double getRotation(){
		return rotationalSignal;
	}
	
	public void limitValues() {
		speedSignal = DriveSignalUtilities.limit(speedSignal, Constants.DRIVE_SPEED_LIMIT);
		rotationalSignal = DriveSignalUtilities.limit(rotationalSignal, Constants.DRIVE_SPEED_LIMIT);
	}
	
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speedSignal + rotationalSignal;
		double giveRight = speedSignal - rotationalSignal;
		return new DifferentialDriveSignal(giveLeft, giveRight);
	}
}
