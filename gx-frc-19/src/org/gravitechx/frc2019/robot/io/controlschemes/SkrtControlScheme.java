package org.gravitechx.frc2019.robot.io.controlschemes;

import org.gravitechx.frc2019.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class SkrtControlScheme {
	/**
	 * This initializes the only instance of the control scheme 
	 * that will be used at any time. It also allows for the instance
	 * to be called and received from the Robot.java class.
	 */
	private static SkrtControlScheme oneInstance = new SkrtControlScheme();
	public static SkrtControlScheme getInstance() {
		return oneInstance;
	}
	
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	//Constructor
	private SkrtControlScheme() {
		throttleJoystick = new Joystick(Constants.THROTTLE_JOYSTICK_PORT);
		rotationJoystick = new Joystick(Constants.ROTATION_JOYSTICK_PORT);
	}
	
	//getters for joystick values
	public double getThrottle() {
		//System.out.println("THROTTLE: " + throttleJoystick.getY());
		return (Constants.THROTTLE_IS_REVERSED) ? (-throttleJoystick.getY()) : (throttleJoystick.getY());
	}
	
	public double getRotation() {
		//System.out.println("ROTATION: " + rotationJoystick.getX());
		return rotationJoystick.getX();
	}
	
	public boolean getRightTurnButton() {
		return throttleJoystick.getRawButton(Constants.RIGHT_SWIVEL_BUTTON);
	}
	
	public boolean getLeftTurnButton() {
		return throttleJoystick.getRawButton(Constants.LEFT_SWIVEL_BUTTON);
	}
	
	//getters for the sticks
	public Joystick getThrottleStick() {
		return throttleJoystick;
	}
	
	public Joystick getRotationStick() {
		return rotationJoystick;
	}
}
