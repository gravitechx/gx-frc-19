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
	public static final double SPEED_SCALE_VALUE = 0.5;
	public static final double WHEEL_SENSITIVITY_VALUE = 2;
	
	/**
	 * This number (between 0 and 1), represents the reduced speed
	 * of the robot when the lift is up. This number limits the speed
	 * the joystick can give in when trying to drive with the lift. 
	 * NOT CURRENTLY  IMPLEMENTED
	 */
	public static final double SPEED_LIMIT_WHEN_LIFT_UP = 0.25;
	
	
	/**
	 * Indicated the value of deadband that the throttle joystick
	 * should apply. This should be increased if the sensitivity of
	 * the joystick should be increased. The lower this is, the more
	 * prone the robot is to register slight movements and small things
	 * that otherwise should not be included. The number should be
	 * between 0 (no change) and 1 (very insensitive).
	 */
	public static final double THROTTLE_DEADBAND = 0.1;
	
	/** 
	 * This value represents the deadband that should be applied to
	 * the wheel, aka the rotation joystick. This deadband should be
	 * increased to increase the sensitivity of the wheel. A higher
	 * number will make the wheel less responsive to slight changes.
	 * The number should be between 0 (no change) and 1 (very insen-
	 * sitive).
	 */
	public static final double ROTATION_DEADBAND = 0.1;
	
	public static final boolean THROTTLE_IS_REVERSED = true;

	/**
	 * PID Controllers
	 */

	 //Drive PID Settings
	 public static final double kProportional = 0;
	 public static final double kIntegral = 0;
	 public static final double kDerivative = 0;
	 public static final double kFeedForward = 0.237;
	 /*public static final double kProportional = 0.199999809;
	 public static final double kIntegral = 0.000009775162;
	 public static final double kDerivative = 0.149999857;*/
}