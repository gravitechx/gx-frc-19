package org.gravitechx.frc2019.robot.subsystems.BADIOsubsystem;

import org.gravitechx.frc2019.robot.Constants;

public class BADIO {
	
	private RANDOM_MOTOR_CONTROLLER /*fix later please*/ positionMotor;

	private WPI_TalonSRX leftBIO;
	private WPI_TalonSRX rightBIO;
	
	public enum positionOptions {
		ONE, TWO, THREE, FOUR
	}
	
	private positionOptions positionStatus;
	
	private static final BADIO badio = new BADIO();
	
	private BADIO() {
		positionMotor = new RANDOM_MOTOR_CONTROLLER /*FIX LATER*/(Constants.POSITION_MOTOR_PORT);
		positionStatus = positionOptions.ONE;

		leftBIO = new WPI_TalonSRX(Constants.LEFT_BIO_TALON_PORT);
		rightBIO = new WPI_TALONSRX(Constants.RIGHT_BIO_TALON_PORT);
	}
	
	public void setPosition(positionOptions position) {
		if (positionStatus != position) {
			positionStatus = position;
			switch (position) {
			case ONE:
				positionMotor.set(Constants.BADIO_POSITION_ONE);
				break;
			case TWO:
				positionMotor.set(Constants.BADIO_POSITION_TWO);
				break;
			case THREE:
				positionMotor.set(Constants.BADIO_POSITION_THREE);
				break;
			case FOUR: 
				positionMotor.set(Constants.BADIO_POSITION_FOUR);
				break;
			}
		}
	}

	public void allign(positionOptions position) {
		switch (position) {
		case ONE:
			break;
		case TWO: 
			break;
		case THREE:
			break;
		case FOUR: 
			break;
		}
	}

	public void bInput(double speed) {
		leftBIO.set(speed);
		rightBIO.set(speed);
	}
	
	public void bOutput(double speed) {
		leftBIO.set(speed);
		rightBIO.set(speed);
	}

	public void dInput(double speed) {}
	public void dOutput(double speed) {}
	
}
