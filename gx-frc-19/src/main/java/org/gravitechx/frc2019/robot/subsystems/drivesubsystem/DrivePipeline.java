package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;

public class DrivePipeline {
	double quickTurnAccumulator = 0;
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal, boolean leftButton, boolean rightButton, boolean yeetButton) {		
		//applies the Constants.java deadband amounts to both throttle and wheel/rotation joystick
		rotationalDriveSignal.applyDeadband(Constants.THROTTLE_DEADBAND, Constants.ROTATION_DEADBAND);
		
		//scale the wheel
		rotationalDriveSignal.limitWheelSensitivity(Constants.WHEEL_SENSITIVITY_VALUE);

		//scales the joystick values to what we want them to be, adjusting speed
		if (yeetButton){
			rotationalDriveSignal.scaleValues(Constants.SPEED_SCALE_VALUE + (1-Constants.SPEED_SCALE_VALUE));
		} else {
			rotationalDriveSignal.scaleValues(Constants.SPEED_SCALE_VALUE);
		}
		/**
		 * This next segment of code addresses if the top turn buttons
		 * are being pressed. The first if statement makes the contents only
		 * apply if only one of the buttons are being pressed. Then, the
		 * nested if statement addresses if it is the left swivel button, and
		 * the else addresses if the right swivel button is pressed. Both
		 * statements return custom DifferentialDriveSignals that are tailored
		 * to limit constants. These values may need to be adjusted.
		 */ 
		if (!(leftButton && rightButton) && Math.abs(rotationalDriveSignal.getSpeed()) < 0.2){
			if (leftButton){
				return rotationalDriveSignal.toDifferentialDriveSignal(-0.1, 0.1);
			} else if (rightButton){
				return rotationalDriveSignal.toDifferentialDriveSignal(0.1, -0.1);
			}
		}
		/*if (!(leftButton && rightButton)) {
			if (leftButton) {
				//return rotationalDriveSignal.toDifferentialDriveSignal(-1 * Constants.SPEED_SCALE_VALUE * Constants.QUIKTURN_TURN_SPEED_ADJUSTMENT, 1 * Constants.SPEED_SCALE_VALUE * Constants.QUIKTURN_TURN_SPEED_ADJUSTMENT);
				quickTurnAccumulator = (1-Constants.QUIKTURN_WEIGHT) * quickTurnAccumulator + Constants.QUIKTURN_WEIGHT * Constants.QUIKTURN_SCALAR * -1;
			} else if (rightButton) {
				//return rotationalDriveSignal.toDifferentialDriveSignal(1 * Constants.SPEED_SCALE_VALUE * Constants.QUIKTURN_TURN_SPEED_ADJUSTMENT, -1 * Constants.SPEED_SCALE_VALUE * Constants.QUIKTURN_TURN_SPEED_ADJUSTMENT);
				quickTurnAccumulator = (1-Constants.QUIKTURN_WEIGHT) * quickTurnAccumulator + Constants.QUIKTURN_WEIGHT * Constants.QUIKTURN_SCALAR * 1;
			}
		} else {
			rotationalDriveSignal.setRotation(Math.abs(rotationalDriveSignal.getSpeed()) * rotationalDriveSignal.getRotation() - quickTurnAccumulator);
			if (quickTurnAccumulator > 1){
				quickTurnAccumulator--;
			} else if (quickTurnAccumulator < -1){
				quickTurnAccumulator++;
			} else {
				quickTurnAccumulator = 0;
			}
		}*/

		//if no buttons are being pressed, convert as regular and return the signal
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}