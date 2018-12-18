package org.gravitechx.frc2019.robot;

public class Constants {
	//these two port values are taken from gx-frc-18 github
	public static final int THROTTLE_JOYSTICK_PORT = 1;
	public static final int ROTATION_JOYSTICK_PORT = 0;
	
	public static final int RIGHT_SWIVEL_BUTTON = 4;
	public static final int LEFT_SWIVEL_BUTTON = 3;
	
	//Values for the motor controller ports
	public static final int LEFT_MASTER_TALON_PORT = 3;
	public static final int RIGHT_MASTER_TALON_PORT = 1;
	public static final int LEFT_SLAVE_VICTOR_PORT = 1;
	public static final int RIGHT_SLAVE_VICTOR_PORT = 3;
	
	/**
	 * The speed scale value is the overall speed scale the robot
	 * should be limited to. The wheel sensitivity value is the value
	 * that the wheel should be as sensitive as. For instance, a value
	 * of (2) would make maximum turn be at 1/2 the wheel turn, as it is
	 * twice as sensitive.
	 */
	public static final double SPEED_SCALE_VALUE = 0.25;
	public static final double WHEEL_SENSITIVITY_VALUE = 2;
	
	/**
	 * This number (between 0 and 1), represents the reduced speed
	 * of the robot when the lift is up. This number limits the speed
	 * the joystick can give in when trying to drive with the lift. 
	 * NOT CURRENTLY  IMPLEMENTED
	 */
	public static final double SPEED_LIMIT_WHEN_LIFT_UP = 0.25;
	
	public static final double THROTTLE_DEADBAND = 0;
	public static final double ROTATION_DEADBAND = 0;
}
