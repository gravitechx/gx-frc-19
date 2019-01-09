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
	
	/* *
	* The limit here indicates the limit of how fast we should go
	* when giving power to the motor controllers. The values that
	* should be put into this constant should be in between 1, the 
	* maximum speed, and 0, or not moving at all.
	* */
	public static final int DRIVE_SPEED_LIMIT = 1;
}
