package org.gravitechx.frc2019.utils.driveutilities;
/**
 * A drive signal with speed and rotation.
 * */
public class RotationalDriveSignal {
	/**
	 * speed
	 * */
	private double speed;
	/**
	 * rotation
	 * */
	private double rotation;
	/**
	 * constructs RotationalDriveSignal setting speed and rotation to respective inputs.
	 * */
	public RotationalDriveSignal(double givenSignal, double givenRotation){
		speed = givenSignal;
		rotation = givenRotation;
	}
	/**
	 * returns speed
	 * */
	public double getSpeed(){
		return speed;
	}
	/**
	 * returns rotation
	 * */
	public double getRotation(){
		return rotation;
	}
	/**
	 * limits speed and rotation using DriveSignalUtilities.limit()
	 * */
	public void limitValues() {
		speed = DriveSignalUtilities.limit(speed);
		rotation = DriveSignalUtilities.limit(rotation);
	}
	/**
	 * converts RotationalDriveSignal to a DifferentialDriveSignal.
	 * */
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speed + rotation;
		double giveRight = speed - rotation;
		return new DifferentialDriveSignal(giveLeft, giveRight);
	}
}
