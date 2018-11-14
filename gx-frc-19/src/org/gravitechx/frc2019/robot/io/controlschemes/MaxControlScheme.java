package org.gravitechx.frc2019.robot.io.controlschemes;

import org.gravitechx.frc2019.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class MaxControlScheme {
	private static MaxControlScheme oneInstance = new MaxControlScheme();
	public static MaxControlScheme getInstance() {
		return oneInstance;
	}
	
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	//Constructor
	private MaxControlScheme() {
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
		return throttleJoystick.getRawButtonPressed(0);
	}
	
	public boolean getLeftTurnButton() {
		return throttleJoystick.getTopPressed();
	}
}
