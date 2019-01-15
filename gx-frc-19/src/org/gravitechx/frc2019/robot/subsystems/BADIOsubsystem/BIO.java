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
	
	public void input(double speed) {
		leftIO.set(speed);
		rightIO.set(speed);
	}
	
	public void output(double speed) {
		leftIO.set(speed);
		rightIO.set(speed);
	}
	
	public static void allignCargo() {
		//our robot is currently blind... will deal with this issue later
	}
	

}
