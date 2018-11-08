package org.gravitechx.frc2019.robot.subsystems;

import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class Drive {
	private WPI_TalonSRX leftMasterTalon;
	private WPI_TalonSRX rightMasterTalon;
	
	private WPI_VictorSPX leftSlave;
	private WPI_VictorSPX rightSlave;
	
	public Drive() {
		leftMasterTalon = new WPI_TalonSRX(Constants.LEFT_MASTER_TALON_PORT);
		rightMasterTalon = new WPI_TalonSRX(Constants.RIGHT_MASTER_TALON_PORT);
		
		leftSlave = new WPI_VictorSPX(Constants.LEFT_SLAVE_VICTOR_PORT);
		rightSlave = new WPI_VictorSPX(Constants.RIGHT_SLAVE_VICTOR_PORT);
		
		leftSlave.follow(leftMasterTalon);
		rightSlave.follow(rightMasterTalon);
	}
	public void go(DifferentialDriveSignal diffSignal) {
		leftMasterTalon.set(ControlMode.PercentOutput, diffSignal.getLeftSide());
		rightMasterTalon.set(ControlMode.PercentOutput, diffSignal.getRightSide());
	}
}
