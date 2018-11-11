package org.gravitechx.frc2019.utils.driveutilities;

public class DrivePipeline {
	 public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		 rotationalDriveSignal.limitValues();
		 return rotationalDriveSignal.toDifferentialDriveSignal();
	 }
}
