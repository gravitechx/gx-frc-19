package org.gravitechx.frc2019.utils.driveutilities;

public class DrivePipeline {
	private DrivePipeline oneInstance = new DrivePipeline();
	public DrivePipeline getInstance(){
		return oneInstance;
	}
	
	private DrivePipeline(){
		
	}
	
	public DifferentialDriveSignal filter(RotationalDriveSignal rotationalDriveSignal) {
		rotationalDriveSignal.limitValues();
		return rotationalDriveSignal.toDifferentialDriveSignal();
	}
}
