package org.gravitechx.frc2019.robot;

public class Constants {
	//these two port values are taken from gx-frc-18 github
	public static final int THROTTLE_JOYSTICK_PORT = 1;
	public static final int ROTATION_JOYSTICK_PORT = 0;
	
	public static final int RIGHT_SWIVEL_BUTTON = 4;
	public static final int LEFT_SWIVEL_BUTTON = 3;
	
	//Values for the motor controller ports
	public static final int LEFT_MASTER_TALON_PORT = 1;
	public static final int RIGHT_MASTER_TALON_PORT = 0;
	public static final int LEFT_SLAVE_VICTOR_PORT_ONE = 1;
	public static final int LEFT_SLAVE_VICTOR_PORT_TWO = 2;
	public static final int RIGHT_SLAVE_VICTOR_PORT_ONE = 5;
	public static final int RIGHT_SLAVE_VICTOR_PORT_TWO = 3;
	
	/**
	 * The speed scale value is the overall speed scale the robot
	 * should be limited to. The wheel sensitivity value is the value
	 * that the wheel should be as sensitive as. For instance, a value
	 * of (2) would make maximum turn be at 1/2 the wheel turn, as it is
	 * twice as sensitive.
	 */
	public static final double SPEED_SCALE_VALUE = 0.4;
	public static final double WHEEL_SENSITIVITY_VALUE = 2;
	public static final double SINGLE_JOYSTICK_ROTATION_VALUE = 0.35;
	public static final double QUIKTURN_TURN_SPEED_ADJUSTMENT = 0.35;
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
	public static final double THROTTLE_DEADBAND = 0.2;
	
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

	public static final double distanceBetweenWheelsMeters = 0.705;
	public static final double ticksToMetersConversionFactor = 0.0000118433895692033811661265341759;
	public static final double maximumTickSpeed = 6900;
	/**
	 * PID Controllers
	 */

	 //Drive PID Settings
	 public static final boolean allowPIDValues = true;
	 /*public static final double kProportional = 0.08;
	 public static final double kIntegral = 0.0000000000001000230050015;
	 public static final double kDerivative = 0.1;*/

	 /*public static final double kProportional = 0.18;
	 public static final double kIntegral = 0;
	 public static final double kDerivative = 0.0294;*/

	 public static final double kProportional = 0.18;
	 public static final double kIntegral = 0;
	 public static final double kDerivative = 0.294;

	 public static final double kFeedForward = 0.32;

	 public static final int allowablePIDError = 20;
}