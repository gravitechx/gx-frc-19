package org.gravitechx.frc2019.utils.driveutilitiesTest;
import org.gravitechx.frc2019.utils.driveutilities.DriveSignalUtilities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DriveSignalUtilitiesTest {
	@ParameterizedTest(name = "{0} is limited within [s | -{1} <= s <= {1} ]")
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
	void testLimit(double signal, double limit, double limited){
		assertEquals(limited, DriveSignalUtilities.limit(signal, limit),
				signal + " is not limited within {s | " + (-limit) + " <= s <= " + limit +" }");
	}
}