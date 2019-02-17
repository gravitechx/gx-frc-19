package org.gravitechx.frc2019.utils.driveutilities;

public class DifferentialDriveSignal {
	private double leftSignal, rightSignal, limitConstant;
	
	public DifferentialDriveSignal(double givenLeft, double givenRight, double limitConstant) {
		leftSignal = givenLeft;
		rightSignal = givenRight;
		this.limitConstant = limitConstant;
	}
	
	public void limitValues() {
		leftSignal = DriveSignalUtilities.limit(leftSignal, limitConstant);
		rightSignal = DriveSignalUtilities.limit(rightSignal, limitConstant);
	}
	
	public double getLeftSide() {
		return leftSignal;
	}
	
	public double getRightSide() {
		return rightSignal;
	}

	public String toString(){
		return "DifferentialDriveSignal[ LeftMotorOutput: " + leftSignal + " RightMotorOutput: " + rightSignal +  " ]";
	}
}
