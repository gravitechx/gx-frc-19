package org.gravitechx.frc2019.robot.io.controlschemes;

import org.gravitechx.frc2019.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
/**
 * The basic control Scheme.
 * */
public class RealControlScheme extends ControlScheme{
	
	/**
	 * private instance of RealControlScheme.  
	 * private used to make RealControlScheme a Singleton. 
	 * */
	private static RealControlScheme oneInstance = new RealControlScheme();
	/**returns the only instance of RealControlScheme*/
	public static RealControlScheme getInstance(){
		return oneInstance;
	}
	/**Joystick used for throttle input (required by parent class)*/
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	/**
	 * constructor that initializes throttleStick and rotationStick with ports specified in the constants file.
	 * It is private so RealControlScheme can only be constructed within itself.
	 * */
	private RealControlScheme() {
		throttleJoystick = new Joystick(Constants.THROTTLE_JOYSTICK_PORT);//Set the throttle joystick to a port specified in constants
		rotationJoystick = new Joystick(Constants.ROTATION_JOYSTICK_PORT);//Set the wheel joystick to a port specified in constants
	}
	
	/**
	 * returns the y input of throttleStick(-1.0 to 1.0 inclusive).
	 * */
	public double getThrottle() {
		return throttleJoystick.getY();
	}
	
	/**
	 * returns the y input of rotationStick(-1.0 to 1.0 inclusive).
	 * */
	public double getRotation() {
		return rotationJoystick.getY();
	}
	/**
	 * returns boolean representing if QuickTurn is active.
	 * */
	public boolean getQuickTurn() {
		
		return false;
	}
}
