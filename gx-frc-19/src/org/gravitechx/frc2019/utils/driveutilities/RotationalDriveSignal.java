package org.gravitechx.frc2019.utils.driveutilities;

import org.gravitechx.frc2019.robot.Constants;

public class RotationalDriveSignal {
	private double speed, rotation;
	
	public RotationalDriveSignal(double givenSignal, double givenRotation){
		speed = givenSignal;
		rotation = givenRotation;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public double getRotation(){
		return rotation;
	}
	
	public void limitValues() {
		speed = DriveSignalUtilities.limit(speed, Constants.DRIVE_SPEED_LIMIT);
		rotation = DriveSignalUtilities.limit(rotation, Constants.DRIVE_SPEED_LIMIT);
	}
	
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speed + rotation;
		double giveRight = speed - rotation;
		return new DifferentialDriveSignal(giveLeft, giveRight);
	}
}
