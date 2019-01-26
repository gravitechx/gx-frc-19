package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;

public class DrivePipeline {
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal, boolean leftButton, boolean rightButton) {
		/**
		 * This next segment of code addresses if the top turn buttons
		 * are being pressed. The first if statement makes the contents only
		 * apply if only one of the buttons are being pressed. Then, the
		 * nested if statement addresses if it is the left swivel button, and
		 * the else addresses if the right swivel button is pressed. Both
		 * statements return custom DifferentialDriveSignals that are tailored
		 * to limit constants. These values may need to be adjusted.
		 */
		/*if (!(leftTurnButton && rightTurnButton)) {
			if (leftTurnButton) {
				return rotationalDriveSignal.toDifferentialDriveSignal(-1 * Constants.SPEED_SCALE_VALUE, 1 * Constants.SPEED_SCALE_VALUE);
			} else if (rightTurnButton) {
				return rotationalDriveSignal.toDifferentialDriveSignal(1 * Constants.SPEED_SCALE_VALUE, -1 * Constants.SPEED_SCALE_VALUE);
			}
		}*/
		
		//applies the Constants.java deadband amounts to both throttle and wheel/rotation joystick
		rotationalDriveSignal.applyDeadband(Constants.THROTTLE_DEADBAND, Constants.ROTATION_DEADBAND);
		
		//scale the wheel
		rotationalDriveSignal.limitWheelSensitivity(Constants.WHEEL_SENSITIVITY_VALUE);

		//scales the joystick values to what we want them to be, adjusting speed
		rotationalDriveSignal.scaleValues(Constants.SPEED_SCALE_VALUE);
		
		//limits the values of the drive to the limit specified in Constants.java
		rotationalDriveSignal.limitValues((1 - (0.0 * Constants.SPEED_LIMIT_WHEN_LIFT_UP)) * Constants.SPEED_SCALE_VALUE);
		//Change this value to lift height      ^^^ Change this when the lift subsystem can give us the number

		if (leftButton || rightButton){
			if (leftButton && !rightButton){
				return rotationalDriveSignal.toDifferentialDriveSignal(-1, 1);
			} else if (rightButton && !leftButton){
				return rotationalDriveSignal.toDifferentialDriveSignal(1, -1);
			}
		}

		//if no buttons are being pressed, convert as regular and return the signal
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}