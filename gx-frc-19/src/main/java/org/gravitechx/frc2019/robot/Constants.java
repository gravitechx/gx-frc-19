package org.gravitechx.frc2019.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.VacuumPosition;

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
	public static final double SPEED_SCALE_VALUE = 0.6;
	public static final double WHEEL_SENSITIVITY_VALUE = 2;
	public static final double SINGLE_JOYSTICK_ROTATION_SCALE_VALUE = 0.35;
	
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

	public static final double MAXIMUM_DRIVE_SPEED_TICKS = 3600;

	/**
	 * PID Controllers
	 */

	 //Drive PID Settings

	public static final boolean CLOSED_LOOP = true;

	 /*public static final double kProportional = 0.5;
	 public static final double kIntegral = 0.00000001;
	 public static final double kDerivative = 0.15;*/

	 public static final double kProportional = 0.5;
	 public static final double kIntegral = 0.00000001;
	 public static final double kDerivative = 0.15;

	 public static final double kFeedForward = 0.34;

	 public static final int allowablePIDError = 20;







	//JOYSTICK STUFF
    //Joystick Constants
    public static final int ARM_JOYSTICK_PORT = 0;

    public static final int BALL_HEIGHT_POSITION_BUTTON = 5;
    public static final int SHUTTLE_SHOOTING_HEIGHT_POSITION_BUTTON = 3;
    public static final int ROCKET_HEIGHT_POSITION_BUTTON = 4;
    public static final int SHUTTLE_HEIGHT_POSITION_BUTTON = 6;

    //Manual Controls
    public static final int TOGGLE_ARM_CONTROL_TYPE_BUTTON = 7;
    public static final int TOGGLE_VACUUM_POSITION_BUTTON = 8;
    public static final int MANUAL_VACUUM_EXHALE_BUTTON = 9;
    public static final int MANUAL_VACUUM_INHALE_BUTTON = 10;
    public static final int MANUAL_HOLDER_EXHALE_BUTTON = 11;
	public static final int MANUAL_HOLDER_INHALE_BUTTON = 12;
	public static final int TOGGLE_PANCAKE = 2;




    //VACUUM STUFF
    //Vacuum Constants
	public static final int LEFT_VACUUM_BIO_VICTOR_PORT = 6;
	public static final int RIGHT_VACUUM_BIO_VICTOR_PORT = 0;
    //Ports for Vacuum Solenoid
    public static final int VACUUM_SOLENOID_MODULENUMBER = 0;
    public static final int VACUUM_SOLENOID_FORWARDCHANNEL = 1;
    public static final int VACUUM_SOLENOID_REVERSECHANNEL = 2;
	
    public static final VacuumPosition START_POSITION = VacuumPosition.UP;

    public static final double VACUUM_EXHALE_SPEED = 0.35;
    public static final double VACUUM_INHALE_SPEED = -0.35;

    //Position of Vacuum (up/down)
    public static final DoubleSolenoid.Value VACUUM_UP = Value.kForward;
    public static final DoubleSolenoid.Value VACUUM_DOWN = Value.kReverse;





    //ARM STUFF
    //Arm constants
    //Motor output equal to that of Gravity
    public static final double ZERO_RADIAN_ENCODER = -Math.PI / 2;

    public static final int ARM_TALON_PORT = 2;
    public static final int ARM_PID_ERROR = 100;
    public static final int ARM_TALON_SENSOR_PORT = 0;
    public static final int ARM_RIGHT_BIO_VICTOR_PORT = 7;
    public static final int ARM_LEFT_BIO_VICTOR_PORT = 4;
    public static final int GRIPPER_SOLENOID_MODULENUMBER = 0;
    public static final int GRIPPER_SOLENOID_CHANNEL = 5;

    
    public static final double STEADY_STATE_VOLTAGE = 0.05; //.20005; //0.05 0.20125;
    public static final double RADIANS_PER_TICK = (Math.PI * 2)/(4096 / Constants.GEAR_RATIO);
    public static final double GEAR_RATIO = 1/23.11;
    //The position where the arm is perpendicular to Earth
    public static final double MIDDLE_ARM_POSITION = (0.5 * Math.PI) / 2;

    //DIMENSIONS = METERS
    public static final double CHASIS_HEIGHT_M = .1;
    public static final double ARM_RADIUS_M = .32;

    //HEIGHTS IN METERS
    public static final double BALL_HEIGHT_M = .1;
    public static final double CARGO_BAY_HEIGHT_M = .52;
    public static final double ROCKET_HEIGHT_M = .70;
    public static final double SHUTTLE_HEIGHT_M = .27;

    public static final double ARM_BIO_EXHALE_SPEED = 0.6;
	public static final double ARM_BIO_INHALE_SPEED = -0.4;
	



	//PANCAKE STUFF
	//Pancake Constants
	public static final int PANCAKE_SOLENOID_MODULENUMBER = 0;
	public static final int PANCAKE_SOLENOID_FORWARDCHANNEL = 0;
	public static final int PANCAKE_SOLENOID_REVERSECHANNEL = 0;

	//Pancake in is holding the disk
	public static final DoubleSolenoid.Value PANCAKE_IN = Value.kReverse;
	public static final DoubleSolenoid.Value PANCAKE_OUT = Value.kForward;
}