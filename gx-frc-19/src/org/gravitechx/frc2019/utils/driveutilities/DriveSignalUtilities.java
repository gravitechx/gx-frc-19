package org.gravitechx.frc2019.utils.driveutilities;

public class DriveSignalUtilities {
	//limit the signal to the specified limit. If above limit, set to limit. If below negative limit, set to negative limit. Else keep the same.
	public double limit(double signal, double limit) {
		signal = (signal > limit) ? limit : ((signal < -limit) ? -limit : signal);
		return signal;
	}
	
	//if only given one value, assume it is the thing to limit. Pass it to above limit function with the limit of one.
	public double limit(double signal) {
		return limit(signal, 1.0);
	}
}
