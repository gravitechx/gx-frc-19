package org.gravitechx.frc2019.utils.driveutilities;

public class RotationalDriveSignal {
	private double speed, rotation;
	private DriveSignalUtilities utility = new DriveSignalUtilities();
	
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
		speed = utility.limit(speed);
		rotation = utility.limit(rotation);
	}
	
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speed + rotation;
		double giveRight = speed - rotation;
		return new DifferentialDriveSignal(giveLeft, giveRight);
	}
}
