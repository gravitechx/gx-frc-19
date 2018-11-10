package org.gravitechx.frc2019.utils.driveutilities;
/**
 * contains static methods used to operate on DriveSignals
 * */
public class DriveSignalUtilities {
	/**
	 * limits the inputed signal to the specified limit. 
	 * If it is above limit, it is set to limit. 
	 * If it is below negative limit, it is set to negative limit. 
	 * Otherwise it is kept the same.
	 * */
	public static double limit(double signal, double limit) {
		signal = (signal > limit) ? limit : ((signal < -limit) ? -limit : signal);
		return signal;
	}
	
	/**
	 * limits inputed signal with limit 1.0
	 * */
	public static double limit(double signal) {
		return limit(signal, 1.0);
	}
}
