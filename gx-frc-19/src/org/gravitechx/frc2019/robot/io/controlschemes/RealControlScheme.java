package org.gravitechx.frc2019.robot.io.controlschemes;

import edu.wpi.first.wpilibj.Joystick;

public class RealControlScheme {
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	//Contructors
	protected RealControlScheme() {
		throttleJoystick = new Joystick(0);//CHANGE TO BE REAL VALUES
		rotationJoystick = new Joystick(1);//CHANGE TO BE REAL VALUES
	}
	
	//Get the value of the acceleration joystick of the driver
	public double getThrottle() {
		return throttleJoystick.getY();
	}
	
	//get the wheel rotation value of the driver
	public double getRotation() {
		return rotationJoystick.getY();
	}
}
