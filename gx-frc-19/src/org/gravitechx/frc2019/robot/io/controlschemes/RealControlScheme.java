package org.gravitechx.frc2019.robot.io.controlschemes;

import edu.wpi.first.wpilibj.Joystick;
import org.gravitechx.frc2019.robot.Constants;

public class RealControlScheme {
	private static RealControlScheme oneInstance = new RealControlScheme();
	private static RealControlScheme getInstance(){
		return oneInstance;
	}
	
	private static Joystick throttleJoystick;
	private static Joystick rotationJoystick;
	
	//Constructors
	public RealControlScheme() {
		throttleJoystick = new Joystick(Constants.THROTTLE_JOYSTICK_PORT);//Set the throttle joystick to a port specified in constants
		rotationJoystick = new Joystick(Constants.ROTATION_JOYSTICK_PORT);//Set the wheel joystick to a port specified in constants
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
