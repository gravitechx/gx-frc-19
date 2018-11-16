package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.robot.io.controlschemes.SkrtControlScheme;

public class DrivePipeline {
	/**
	 * Create the local instance of the control scheme to manipulate
	 * and grab boolean values from. This is to sense if buttons are 
	 * being pressed that would influence the drive values.
	 */
	private SkrtControlScheme driverControls;
	public DrivePipeline(SkrtControlScheme intake) {
		driverControls = intake;
	}
	
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		//limits the values of the drive to the limit specified in Constants.java
		rotationalDriveSignal.limitValues(Constants.DRIVE_SPEED_LIMIT);
		
		//applies the Constants.java deadband amounts to both throttle and wheel/rotation joystick
		rotationalDriveSignal.applyDeadband(Constants.THROTTLE_DEADBAND, Constants.ROTATION_DEADBAND);
		
		/**
		 * This next segment of code addresses if the top turn buttons
		 * are being pressed. The first if statement makes the contents only
		 * apply if only one of the buttons are being pressed. Then, the
		 * nested if statement addresses if it is the left swivel button, and
		 * the else addresses if the right swivel button is pressed. Both
		 * statements return custom DifferentialDriveSignals that are tailored
		 * to limit constants. These values may need to be adjusted.
		 */
		if (!(driverControls.getLeftTurnButton() && driverControls.getRightTurnButton())) {
			if (driverControls.getLeftTurnButton()) {
				return rotationalDriveSignal.toDifferentialDriveSignal(-1 * Constants.DRIVE_SPEED_LIMIT, 1 * Constants.DRIVE_SPEED_LIMIT);
			} else {
				return rotationalDriveSignal.toDifferentialDriveSignal(1 * Constants.DRIVE_SPEED_LIMIT, -1 * Constants.DRIVE_SPEED_LIMIT);
			}
		}
		
		//if no buttons are being pressed, convert as regular and return the signal
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}
