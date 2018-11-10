import static org.junit.jupiter.api.Assertions.*;

import org.gravitechx.frc2019.utils.driveutilities.DrivePipeline;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.DriveSignalUtilities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LimitTest {

	@Test
	@DisplayName("Limit")
	public void testLimit() {
		RotationalDriveSignal drive;
		
		
		// Testing limits for positive speed
		drive = new RotationalDriveSignal(5, 0);
		drive.limitValues();
		assertEquals(1, drive.getSpeed());
		assertEquals(0, drive.getRotation());

		// Testing limits for negative speed
		drive = new RotationalDriveSignal(-2, -0.01);
		drive.limitValues();
		assertEquals(-1, drive.getSpeed());
		assertEquals(-.01, drive.getRotation());

		// Testing limits for positive rotation
		drive = new RotationalDriveSignal(0.2, 5);
		drive.limitValues();
		assertEquals(0.2, drive.getSpeed());
		assertEquals(1, drive.getRotation());

		// Testing limits for negative rotation
		drive = new RotationalDriveSignal(.2, -1.01);
		drive.limitValues();
		assertEquals(0.2, drive.getSpeed());
		assertEquals(-1, drive.getRotation());
	}

	@Test
	@DisplayName("Differential Drive Signal")
	public void testToDifferentialDriveSignal() {
		RotationalDriveSignal R_Drive;
		DifferentialDriveSignal D_Drive;

		//Limit Test #1 Motor Outputs
		R_Drive = new RotationalDriveSignal(1, 1);
		R_Drive.limitValues();
		D_Drive = R_Drive.toDifferentialDriveSignal();
		assertEquals(1, D_Drive.getLeftSide());
		assertEquals(0, D_Drive.getRightSide());

		//Limit Test #2 Motor Outputs
		R_Drive = new RotationalDriveSignal(1, -1);
		R_Drive.limitValues();
		D_Drive = R_Drive.toDifferentialDriveSignal();
		assertEquals(0, D_Drive.getLeftSide());
		assertEquals(1, D_Drive.getRightSide());
		
		//Limit Test #3 Motor Outputs
		R_Drive = new RotationalDriveSignal(-1, 1);
		R_Drive.limitValues();
		D_Drive = R_Drive.toDifferentialDriveSignal();
		assertEquals(-1, D_Drive.getLeftSide());
		assertEquals(0, D_Drive.getRightSide());
		
		//Limit Test #4 Motor Outputs
		R_Drive = new RotationalDriveSignal(-1, -1);
		R_Drive.limitValues();
		D_Drive = R_Drive.toDifferentialDriveSignal();
		assertEquals(0, D_Drive.getLeftSide());
		assertEquals(-1, D_Drive.getRightSide());
	}
}
