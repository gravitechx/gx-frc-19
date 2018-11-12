package org.gravitechx.frc2019.utils.driveutilities;

public class RotationalDriveSignal {
	private double speedSignal, rotationalSignal, limitConstant;
	
	public RotationalDriveSignal(double givenSignal, double givenRotation, double limitConstant){
		speedSignal = givenSignal;
		rotationalSignal = givenRotation;
		this.limitConstant = limitConstant;
	}
	
	public double getSpeed(){
		return speedSignal;
	}
	
	public double getRotation(){
		return rotationalSignal;
	}
	
	public void limitValues() {
		speedSignal = DriveSignalUtilities.limit(speedSignal, limitConstant);
		rotationalSignal = DriveSignalUtilities.limit(rotationalSignal, limitConstant);
	}
	
	public DifferentialDriveSignal toDifferentialDriveSignal() {
		double giveLeft = speedSignal + rotationalSignal;
		double giveRight = speedSignal - rotationalSignal;
		return new DifferentialDriveSignal(giveLeft, giveRight, limitConstant);
	}

	public String toString(){
		return "RotationalDriveSignal[ Rotational: " + rotation + " Linear: " + speed +  " ]";
	}
}
