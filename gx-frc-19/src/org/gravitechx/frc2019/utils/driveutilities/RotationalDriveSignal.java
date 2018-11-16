package org.gravitechx.frc2019.utils.driveutilities;

public class RotationalDriveSignal {
	private double speedSignal, rotationalSignal, limitConstant;
	
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
	
	public void setSpeed(double speed) {
		speedSignal = speed;
	}
	
	public void setRotation(double rotation) {
		rotationalSignal = rotation;
	}
	
	public void limitValues(double limitConstant) {
		speedSignal = DriveSignalUtilities.limit(speedSignal, limitConstant);
		rotationalSignal = DriveSignalUtilities.limit(rotationalSignal, limitConstant);
	}
	
	public void applyDeadband(double throttleDeadband, double rotationalDeadband) {
		speedSignal = DriveSignalUtilities.applyDeadband(speedSignal, throttleDeadband);
		rotationalSignal = DriveSignalUtilities.applyDeadband(rotationalSignal, rotationalDeadband);
	}
	
	//converts the class variables that have been manipulated to a DifferentialDriveSignal
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speedSignal + rotationalSignal;
		double giveRight = speedSignal - rotationalSignal;
		return new DifferentialDriveSignal(giveLeft, giveRight, limitConstant);
	}
	
	//returns a custom DifferentialDriveSignal with values that are given
	public DifferentialDriveSignal toDifferentialDriveSignal(double giveLeft, double giveRight) {
		return new DifferentialDriveSignal(giveLeft, giveRight, limitConstant);
	}

	public String toString(){
		return "RotationalDriveSignal[ Linear: " + speedSignal +  " + Rotational: " + rotationalSignal + " ]";
	}
}
