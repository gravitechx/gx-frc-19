package org.gravitechx.frc2019.utils.driveutilities;

import org.gravitechx.frc2019.robot.Constants;

public class RotationalDriveSignal {
	private double speedSignal, rotationalSignal, limitConstant;
	
	public RotationalDriveSignal(double givenSignal, double givenRotation){
		speedSignal = givenSignal;
		rotationalSignal = givenRotation;
		limitConstant = Constants.SPEED_SCALE_VALUE;
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
	
	public void scaleValues(double speedScaleConstant) {
		speedSignal = DriveSignalUtilities.scale(speedSignal,speedScaleConstant);
		rotationalSignal = DriveSignalUtilities.scale(rotationalSignal, speedScaleConstant);
	}
	
	public void limitWheelSensitivity(double sensitivity) {
		rotationalSignal = DriveSignalUtilities.scale(rotationalSignal, sensitivity);
		rotationalSignal = DriveSignalUtilities.limit(rotationalSignal);
	}
	
	public void applyDeadband(double throttleDeadband, double rotationalDeadband) {
		speedSignal = DriveSignalUtilities.applyDeadband(speedSignal, throttleDeadband);
		rotationalSignal = DriveSignalUtilities.applyDeadband(rotationalSignal, rotationalDeadband);
	}
	
	//converts the class variables that have been manipulated to a DifferentialDriveSignal
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		//You multiply by the Math.abs speed signal so that you never can move without using throttle joystick
		double giveLeft = speedSignal + (rotationalSignal);
		double giveRight = speedSignal - (rotationalSignal);
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