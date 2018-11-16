package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.robot.io.controlschemes.SkrtControlScheme;

public class DrivePipeline {
	private SkrtControlScheme driverControls;
	public DrivePipeline(SkrtControlScheme intake) {
		driverControls = intake;
	}
	
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		rotationalDriveSignal.limitValues(Constants.DRIVE_SPEED_LIMIT);
		rotationalDriveSignal.applyDeadband(Constants.THROTTLE_DEADBAND, Constants.ROTATION_DEADBAND);
		
		if (!(driverControls.getLeftTurnButton() && driverControls.getRightTurnButton())) {
			if (driverControls.getLeftTurnButton()) {
				return rotationalDriveSignal.toDifferentialDriveSignal(-1 * Constants.DRIVE_SPEED_LIMIT, 1 * Constants.DRIVE_SPEED_LIMIT);
			} else {
				return rotationalDriveSignal.toDifferentialDriveSignal(1 * Constants.DRIVE_SPEED_LIMIT, -1 * Constants.DRIVE_SPEED_LIMIT);
			}
		}
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}
