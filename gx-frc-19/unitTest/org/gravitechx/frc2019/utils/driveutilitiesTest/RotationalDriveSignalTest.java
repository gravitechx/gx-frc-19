package org.gravitechx.frc2019.utils.driveutilitiesTest;
import static org.junit.jupiter.api.Assertions.*;

import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RotationalDriveSignalTest {
	
	@Test
	@DisplayName("toDifferentialDriveSignal method test in RotationalDriveSignal Class")
	public void testToDifferentialDriveSignal() {
		RotationalDriveSignal r_drive;
		DifferentialDriveSignal d_drive;

		//Limit Test #1 Motor Outputs
		r_drive = new RotationalDriveSignal(1, 1);
		r_drive.limitValues();
		d_drive = r_drive.toDifferentialDriveSignal();
		assertEquals(1, d_drive.getLeftSide());
		assertEquals(0, d_drive.getRightSide());

		//Limit Test #2 Motor Outputs
		r_drive = new RotationalDriveSignal(1, -1);
		r_drive.limitValues();
		d_drive = r_drive.toDifferentialDriveSignal();
		assertEquals(0, d_drive.getLeftSide());
		assertEquals(1, d_drive.getRightSide());
		
		//Limit Test #3 Motor Outputs
		r_drive = new RotationalDriveSignal(-1, 1);
		r_drive.limitValues();
		d_drive = r_drive.toDifferentialDriveSignal();
		assertEquals(-1, d_drive.getLeftSide());
		assertEquals(0, d_drive.getRightSide());
		
		//Limit Test #4 Motor Outputs
		r_drive = new RotationalDriveSignal(-1, -1);
		r_drive.limitValues();
		d_drive = r_drive.toDifferentialDriveSignal();
		assertEquals(0, d_drive.getLeftSide());
		assertEquals(-1, d_drive.getRightSide());
	}
}
