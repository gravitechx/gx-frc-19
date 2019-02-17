package org.gravitechx.frc2019.utils.driveutilitiesTest;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationalDriveSignalTest {
	private RotationalDriveSignal rDrive;
	private DifferentialDriveSignal dDrive;

	@ParameterizedTest(name = "RotationalDriveSignal[ Rotational: {0} Linear: {1} ] -> DifferentialDriveSignal[ LeftMotorOutput: {2} RightMotorOutput: {3} ]")
	@CsvSource({
			"1, 0, 1, 1",
			"1, 1, 2, 0",
			"1, -1, 0, 2",
			"-1, 1, 0, -2",
			"-1, -1, -2, 0"
	})
	void testToDifferentialDriveSignal(double linearV, double rotationalV, double leftV, double rightV){
		rDrive = new RotationalDriveSignal(linearV, rotationalV);
		DifferentialDriveSignal target = new DifferentialDriveSignal(leftV, rightV);
		dDrive = rDrive.toDifferentialDriveSignal();
		assertEquals(target.getLeftSide(), dDrive.getLeftSide());
		assertEquals(target.getRightSide(), dDrive.getRightSide());
	}
}
