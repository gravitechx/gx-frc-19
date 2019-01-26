package org.gravitechx.frc2019.robot.io.controlschemes;

import edu.wpi.first.wpilibj.Joystick;
import org.gravitechx.frc2019.robot.Constants;

public class JoystickControlScheme {
	private static JoystickControlScheme oneInstance = new JoystickControlScheme();
	public static JoystickControlScheme getInstance(){
		return oneInstance;
	}
	
	private static Joystick oneJoystick;
	
	//Constructors
	private JoystickControlScheme() {
		oneJoystick = new Joystick(Constants.THROTTLE_JOYSTICK_PORT);//Set the throttle joystick to a port specified in constants
	}
	
	//Get the value of the acceleration joystick of the driver
	public double getThrottle() {
		return (Constants.THROTTLE_IS_REVERSED) ? (-oneJoystick.getY()) : (oneJoystick.getY());
	}
	
	//get the wheel rotation value of the driver
	public double getRotation() {
		return oneJoystick.getX() * ((-0.5/Constants.SPEED_SCALE_VALUE) * getThrottle() + 1);
	}

	public boolean getLeftSkrtTurn(){
		return oneJoystick.getRawButton(3);
	}
	
	public boolean getRightSkrtTurn(){
		return oneJoystick.getRawButton(4);
	}
}