package org.gravitechx.frc2019.robot.io.controlschemes;

import org.gravitechx.frc2019.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class SkrtControlScheme {
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
	
	public double getThrottle() {
		return throttleJoystick.getY();
	}
	
	public double getRotation() {
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
