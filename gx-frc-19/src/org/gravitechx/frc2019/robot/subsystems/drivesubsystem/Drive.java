package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
/**
 * Drive subsystem
 * */
public class Drive {
	
	/**
	 * private instance of Drive.  
	 * private used to make Drive a Singleton. 
	 * */
	private static Drive oneInstance = new Drive();
	
	/**returns the only instance of Drive*/
	public static Drive getInstance(){
		return oneInstance;
	}
	/**
	 * the left TalonSRX
	 * */
	private WPI_TalonSRX leftMasterTalon;
	/**
	 * the right TalonSRX
	 * */
	private WPI_TalonSRX rightMasterTalon;
	/**
	 * the left VictorSPX
	 * */
	private WPI_VictorSPX leftSlave;
	/**
	 * the right VictorSPX
	 * */
	private WPI_VictorSPX rightSlave;
	
	private Drive() {
		leftMasterTalon = new WPI_TalonSRX(Constants.LEFT_MASTER_TALON_PORT);
		rightMasterTalon = new WPI_TalonSRX(Constants.RIGHT_MASTER_TALON_PORT);
		
		leftSlave = new WPI_VictorSPX(Constants.LEFT_SLAVE_VICTOR_PORT);
		rightSlave = new WPI_VictorSPX(Constants.RIGHT_SLAVE_VICTOR_PORT);
		
		leftSlave.follow(leftMasterTalon);
		rightSlave.follow(rightMasterTalon);
	}
	
	public void set(DifferentialDriveSignal diffSignal) {
		diffSignal.limitValues();
		leftMasterTalon.set(ControlMode.PercentOutput, diffSignal.getLeftSide());
		rightMasterTalon.set(ControlMode.PercentOutput, -diffSignal.getRightSide());
	}
}
