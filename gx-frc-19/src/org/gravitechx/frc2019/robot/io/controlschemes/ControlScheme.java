package org.gravitechx.frc2019.robot.io.controlschemes;

import edu.wpi.first.wpilibj.Joystick;
/**
	 * An abstract class for for controlScheme subclasses.
	 * */
public abstract class ControlScheme {
	/**
	 * Joystick used for throttle input
	 * */
	private static Joystick throttleJoystick;
	/**
	 * Joystick used for rotation input
	 * */
	private static Joystick rotationJoystick;
	/**
	 * method used to get throttle input
	 * */
	public abstract double getThrottle();
	/**
	 * method used to get rotation input
	 * */
	public abstract double getRotation();
	/**
	 * method used to check if QuickTurn is active
	 * */
	public abstract boolean getQuickTurn();
	
}
