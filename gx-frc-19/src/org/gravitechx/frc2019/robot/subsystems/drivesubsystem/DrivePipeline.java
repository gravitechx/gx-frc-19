package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;

public class DrivePipeline {
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		rotationalDriveSignal.limitValues();
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}
