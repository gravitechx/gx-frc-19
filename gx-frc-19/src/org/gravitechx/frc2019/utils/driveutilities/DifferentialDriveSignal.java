package org.gravitechx.frc2019.utils.driveutilities;
/**
 * A drive signal with left and right motor outputs.
 * */
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
	/**
	 * returns leftSideOutput
	 * */
	public double getLeftSide() {
		return leftSignal;
	}
	/**
	 * returns rightSideOutput
	 * */
	public double getRightSide() {
		return rightSignal;
	}

	public String toString(){
		return "DifferentialDriveSignal[ LeftMotorOutput: " + leftSideOutput + " RightMotorOutput: " + rightSideOutput +  " ]";
	}
}
