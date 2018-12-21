package org.gravitechx.frc2019.utils.driveutilities;
import org.gravitechx.frc2019.utils.driveutilities.DriveSignalUtilities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DriveSignalUtilitiesTest {
	@ParameterizedTest(name = "{0} is limited within [s | -{1} <= s <= {1}]")
	@CsvSource({
			// Normal limit of one
			"-2, 1, -1",
			"2, 1, 1",
			"0, 1, 0",
			"-.5, 1, -.5",
			// More constructive limit
			"-2, .5, -.5",
			"1, .5, .5",
			".25, .5, .25"
	})
	void testLimit(double signal, double limit, double result){
		assertEquals(result, DriveSignalUtilities.limit(signal, limit),
				signal + " is not limited within {s | " + (-limit) + " <= s <= " + limit +" }");
	}
	
	@ParameterizedTest(name = "{0} is scaled by {1} to a result of {2}")
	@CsvSource({
		// *long sigh*
		// mitochondria is the powerhouse of the cell
		"0.5, 0.5, 0.25",
		"1.2, 1.5, 1.8",
		"-2, 2, -4",
		"0.11, 2, 0.22"
		// "I chose to sat here" --Nira 2018
	})
	void testScale(double signal, double scale, double result) {
		assertEquals(result, DriveSignalUtilities.scale(signal, scale), 
				0.0, (signal + " * " + scale + " does not scale to " + result));
	}
	
	@ParameterizedTest(name = "{1} of deadband is applied to {0}")
	@CsvSource({
		"-0.01, 0.1, 0",
		"0.5, 0.1, 0.44",
		"0.02, 0.1, 0",
		"-0.7, 0.1, -0.67"
	})
	void testDeadband(double signal, double deadband, double result) {
		assertEquals(result, DriveSignalUtilities.applyDeadband(signal, deadband),
				1E-2, signal + "comes out to an incorrect value when a deadband of "
				+ deadband + "is applied.");
	}
}