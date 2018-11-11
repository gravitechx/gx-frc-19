package org.gravitechx.frc2019.utils.driveutilities;

import org.gravitechx.frc2019.robot.Constants;

public class DifferentialDriveSignal {
	private double leftSideOutput;
	private double rightSideOutput;
	
	public DifferentialDriveSignal(double givenLeft, double givenRight) {
		leftSideOutput = givenLeft;
		rightSideOutput = givenRight;
	}
	
	public void limitValues() {
		leftSideOutput = DriveSignalUtilities.limit(leftSideOutput, Constants.DRIVE_SPEED_LIMIT);
		rightSideOutput = DriveSignalUtilities.limit(rightSideOutput, Constants.DRIVE_SPEED_LIMIT);
	}
	
	public double getLeftSide() {
		return leftSideOutput;
	}
	
	public double getRightSide() {
		return rightSideOutput;
	}
}
