package org.gravitechx.frc2019.utils.driveutilities;
/**
 * contains methods to operate on a RotationalDriveSignal
 * */
public class DrivePipeline {
	/**
	 * limits a RotationalDriveSignal's speed and rotation using RotationalDriveSignal.limitValues()
	 * converts it to a DifferentialDriveSignal using RotationalDriveSignal.toDifferentialDriveSignal()
	 * */ 
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		 rotationalDriveSignal.limitValues();
		 return rotationalDriveSignal.toDifferentialDriveSignal();
	 }
}
