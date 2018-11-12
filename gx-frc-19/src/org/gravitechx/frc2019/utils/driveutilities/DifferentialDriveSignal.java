package org.gravitechx.frc2019.utils.driveutilities;

import org.gravitechx.frc2019.robot.Constants;

public class DifferentialDriveSignal {
	private double leftSignal;
	private double rightSignal;
	
	public DifferentialDriveSignal(double givenLeft, double givenRight) {
		leftSignal = givenLeft;
		rightSignal = givenRight;
	}
	
	public void limitValues() {
		leftSignal = DriveSignalUtilities.limit(leftSignal, Constants.DRIVE_SPEED_LIMIT);
		rightSignal = DriveSignalUtilities.limit(rightSignal, Constants.DRIVE_SPEED_LIMIT);
	}
	
	public double getLeftSide() {
		return leftSignal;
	}
	
	public double getRightSide() {
		return rightSignal;
	}
}
