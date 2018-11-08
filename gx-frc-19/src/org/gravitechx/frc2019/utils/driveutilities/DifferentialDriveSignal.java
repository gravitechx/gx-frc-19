package org.gravitechx.frc2019.utils.driveutilities;

public class DifferentialDriveSignal {
	private double leftSideOutput;
	private double rightSideOutput;
	
	public DifferentialDriveSignal(double givenLeft, double givenRight) {
		leftSideOutput = givenLeft;
		rightSideOutput = givenRight;
	}
	
	public double getLeftSide() {
		return leftSideOutput;
	}
	
	public double getRightSide() {
		return rightSideOutput;
	}
}
