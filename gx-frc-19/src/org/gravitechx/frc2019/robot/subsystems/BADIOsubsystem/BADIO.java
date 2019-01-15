package org.gravitechx.frc2019.robot.subsystems.BADIOsubsystem;

import org.gravitechx.frc2019.robot.Constants;

public class BADIO {
	
	private RANDOM_MOTOR_CONTROLLER /*fix later please*/ position;
	
	public enum positionOptions {
		ONE, TWO, THREE, FOUR
	}
	
	private positionOptions positionStatus;
	
	private static final BADIO badio = new BADIO();
	
	private BADIO() {
		position = new RANDOM_MOTOR_CONTROLLER /*FIX LATER*/(Constants.POSITION_MOTOR_PORT);
		positionStatus = positionOptions.ONE;
	}
	
	public void setPosition(positionOptions position) {
		if (positionStatus != position) {
			switch (position) {
			case ONE:
				position.set(Constants.BADIO_POSITION_ONE);
				break;
			case TWO:
				position.set(Constants.BADIO_POSITION_TWO);
				break;
			case THREE:
				position.set(Constants.BADIO_POSITION_THREE);
				break;
			case FOUR: 
				position.set(Constants.BADIO_POSITION_FOUR);
				break;
			}
		}
		
		public void allign(BIO bio, DIO dio) {
			switch (position) {
			case ONE:
				break;
			case TWO: 
				
			}
		}
	}
	
}
