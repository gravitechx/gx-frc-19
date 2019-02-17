package org.gravitechx.frc2019.robot;

public class Constants {
	//these two port values are taken from gx-frc-18 github
	public static final int THROTTLE_JOYSTICK_PORT = 1;
	public static final int ROTATION_JOYSTICK_PORT= 0;
	
	//Values for the motor controller ports
	public static final int LEFT_MASTER_TALON_PORT = 3;
	public static final int RIGHT_MASTER_TALON_PORT = 1;
	public static final int LEFT_SLAVE_VICTOR_PORT = 1;
	public static final int RIGHT_SLAVE_VICTOR_PORT = 3;

	/**
	* The limit here indicates the limit of how fast we should go
	* when giving power to the motor controllers. The values that
	* should be put into this constant should be in between 1, the 
	* maximum speed, and 0, or not moving at all.
	* */
	public static final double DRIVE_SPEED_LIMIT = 1;
	
	//BIO
	public static final double INPUT_SPEED = 0; //CHANGE LATER
	public static final double OUTPUT_SPEED = 0; //CHANGE LATER
	public static final double BADIO_POSITION_ONE = 0; //CHANGE LATER
	public static final double BADIO_POSITION_TWO = 0; //CHANGE LATER
	public static final double BADIO_POSITION_THREE = 0; //CHANGE LATER
	public static final double BADIO_POSITION_FOUR = 0; //CHANGE LATER
	
	public static final int LEFT_BIO_TALON_PORT = 0; //CHANGE LATER
	public static final int RIGHT_BIO_TALON_PORT = 0; //CHANGE LATER
	public static final int POSITION_MOTOR_PORT = 0; //CHANGE LATER
	
}
