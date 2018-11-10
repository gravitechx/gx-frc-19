package org.gravitechx.frc2019.utils.driveutilitiesTest;
import static org.junit.jupiter.api.Assertions.*;
import org.gravitechx.frc2019.utils.driveutilities.DriveSignalUtilities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DriverSignalUtilitiesTest {
	
	@SuppressWarnings("static-access")
	@Test
	@DisplayName("DriveSignalUtilities Limit Method")
	public void testLimit() {
		DriveSignalUtilities signal = new DriveSignalUtilities();
		
		//Tests 1st limit method in DriverSignalUtilities class
		for (double i = -2; i <= 2; i ++) {
			if (i > 1) assertEquals(1, signal.limit(i, 1));
			else if (i < -1) assertEquals(-1, signal.limit(i, 1)); 
			else assertEquals(i, signal.limit(i, 1));
		}
		
		//Tests 2nd limit method in DriverSignalUtilities class
		for (double i = -2; i <= 2; i ++) {
			if (i > 1) assertEquals(1, signal.limit(i));
			else if (i < -1) assertEquals(-1, signal.limit(i)); 
			else assertEquals(i, signal.limit(i));
		}
		
	}
}