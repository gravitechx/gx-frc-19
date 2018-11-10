package org.gravitechx.frc2019.utils.driveutilities;
/**
 * A drive signal with left and right motor outputs.
 * */
public class DifferentialDriveSignal {
	
	/**
	 * left motor output
	 * */
	private double leftSideOutput;
	/**
	 * right motor output
	 * */
	private double rightSideOutput;
	/**
	 * constructs DifferentialDriveSignal setting leftSideOuptut and rightSideOutput to respective inputs.
	 * */
	public DifferentialDriveSignal(double givenLeft, double givenRight) {
		leftSideOutput = givenLeft;
		rightSideOutput = givenRight;
	}
	/**
	 * returns leftSideOutput
	 * */
	public double getLeftSide() {
		return leftSideOutput;
	}
	/**
	 * returns rightSideOutput
	 * */
	public double getRightSide() {
		return rightSideOutput;
	}
}
