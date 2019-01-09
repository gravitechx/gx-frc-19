package org.gravitechx.frc2019.robot.io.controlschemes;

import org.gravitechx.frc2019.robot.Constants;

public class DefaultControlScheme {
	private static DefaultControlScheme oneInstance = new DefaultControlScheme();
	public static DefaultControlScheme getInstance(){
		return oneInstance;
	}
	/**Joystick used for throttle input (required by parent class)*/
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	//Constructors
	private DefaultControlScheme() {
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
		return rotationJoystick.getX();
	}
	/**
	 * returns boolean representing if QuickTurn is active.
	 * */
	public boolean getQuickTurn() {
		
		return false;
	}
}
