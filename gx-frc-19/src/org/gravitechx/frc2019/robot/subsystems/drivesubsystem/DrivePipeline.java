package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;

public class DrivePipeline {
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		rotationalDriveSignal.limitValues(Constants.DRIVE_SPEED_LIMIT);
		rotationalDriveSignal.applyDeadband(Constants.THROTTLE_DEADBAND, Constants.ROTATION_DEADBAND);
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}
