package org.gravitechx.frc2019.utils.driveutilities;
/**
 * A drive signal with speed and rotation.
 * */
public class RotationalDriveSignal {
	private double speedSignal, rotationalSignal, limitConstant;
	
	public RotationalDriveSignal(double givenSignal, double givenRotation, double limitConstant){
		speedSignal = givenSignal;
		rotationalSignal = givenRotation;
		this.limitConstant = limitConstant;
	}
	/**
	 * returns speed
	 * */
	public double getSpeed(){
		return speedSignal;
	}
	/**
	 * returns rotation
	 * */
	public double getRotation(){
		return rotationalSignal;
	}
	/**
	 * limits speed and rotation using DriveSignalUtilities.limit()
	 * */
	public void limitValues() {
		speedSignal = DriveSignalUtilities.limit(speedSignal, limitConstant);
		rotationalSignal = DriveSignalUtilities.limit(rotationalSignal, limitConstant);
	}
	/**
	 * converts RotationalDriveSignal to a DifferentialDriveSignal.
	 * */
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speedSignal + rotationalSignal;
		double giveRight = speedSignal - rotationalSignal;
		return new DifferentialDriveSignal(giveLeft, giveRight, limitConstant);
	}

	public String toString(){
		return "RotationalDriveSignal[ Rotational: " + rotation + " Linear: " + speed +  " ]";
	}
}
