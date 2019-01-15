package org.gravitechx.frc2019.robot.subsystems.BADIOsubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.robot.subsystems.drivesubsystem.WPI_TalonSRX;

public class BIO {
	
	private WPI_TalonSRX leftIO;
	private WPI_TalonSRX rightIO;
	
	private static final BIO bio = new BIO();
	
	private BIO() {
		leftIO = new WPI_TalonSRX(Constants.LEFT_BIO_TALON_PORT);
		rightIO = new WPI_TALONSRX(Constants.RIGHT_BIO_TALON_PORT);
	}
	
	public void input(int speed) {
		leftIO.set(Constants.INPUT_SPEED);
		rightIO = Constants.INPUT_SPEED;
	}
	
	public void output() {
		leftIO = Constants.OUTPUT_SPEED;
		rightIO = Constants.OUTPUT_SPEED;
	}
	
	

}
