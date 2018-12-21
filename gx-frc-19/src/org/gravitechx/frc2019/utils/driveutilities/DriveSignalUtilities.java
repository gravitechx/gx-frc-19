package org.gravitechx.frc2019.utils.driveutilities;

public class DriveSignalUtilities {
	//limit the signal to the specified limit. If above limit, set to limit. If below negative limit, set to negative limit. Else keep the same.
	public static double limit(double signal, double limit) {
		signal = (signal > limit) ? limit : ((signal < -limit) ? -limit : signal);
		return signal;
	}
	
	//if only given one value, assume it is the thing to limit. Pass it to above limit function with the limit of one.
	public static double limit(double signal) {
		return limit(signal, 1.0);
	}
	
	//scales the given signal value to the scale double value
	public static double scale(double signal, double scale) {
		return signal * scale;
	}
	
	public static double applyDeadband(double signal, double deadband) {
		signal = (Math.abs(signal) > deadband) ? ((signal > 0) ? (1/(1 - deadband)) * (signal - deadband) : (1/(1 - deadband)) * (signal + deadband)) : 0.0;
		return signal;
	}
}
