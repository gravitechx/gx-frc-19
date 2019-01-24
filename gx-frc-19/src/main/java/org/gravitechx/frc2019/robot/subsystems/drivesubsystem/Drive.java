package org.gravitechx.frc2019.robot.subsystems.drivesubsystem;

import org.gravitechx.frc2019.utils.driveutilities.DifferentialDriveSignal;
import org.gravitechx.frc2019.utils.motorcontrollers.DriveTalonSRX;
import org.gravitechx.frc2019.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class Drive {
	private static Drive oneInstance = new Drive();
	public static Drive getInstance(){
		return oneInstance;
	}
	
	private TalonSRX leftMasterTalon;
	private TalonSRX rightMasterTalon;
	
	private VictorSPX leftSlave;
	private VictorSPX rightSlave;
	
	private Drive() {
		leftMasterTalon = new WPI_TalonSRX(Constants.LEFT_MASTER_TALON_PORT);
		rightMasterTalon = new WPI_TalonSRX(Constants.RIGHT_MASTER_TALON_PORT);

		leftSlave = new WPI_VictorSPX(Constants.LEFT_SLAVE_VICTOR_PORT);
		rightSlave = new WPI_VictorSPX(Constants.RIGHT_SLAVE_VICTOR_PORT);
		
		leftMasterTalon = DriveTalonSRX.configure(leftMasterTalon);
		rightMasterTalon = DriveTalonSRX.configure(rightMasterTalon);
		rightMasterTalon.setInverted(true);

		leftSlave.follow(leftMasterTalon);
		rightSlave.follow(rightMasterTalon);
	}

	/*public double getVelocity(){
		return rightMasterTalon.getSelectedSensorVelocity(0)*10.0/4096.0*2.0*Math.PI*2.54/100.0;
	}*/
	
	public void set(DifferentialDriveSignal diffSignal) {
		diffSignal.limitValues();
		System.out.println("ERROR: " + leftMasterTalon.getClosedLoopError(0));
		System.out.println("TICKS: " + leftMasterTalon.getSelectedSensorVelocity(0));
		//leftMasterTalon.set(ControlMode.Velocity, 1500);
		//leftMasterTalon.set(ControlMode.Velocity, 1500);
		leftMasterTalon.set(ControlMode.Velocity, 1000);
		rightMasterTalon.set(ControlMode.Velocity, -1000);
		//leftMasterTalon.set(ControlMode.PercentOutput,1.0);
		//rightMasterTalon.set(ControlMode.PercentOutput,-1.0);
		//System.out.print(getVelocity());
	}
	public void brakeTalons(){
		leftMasterTalon.setNeutralMode(NeutralMode.Brake);
		rightMasterTalon.setNeutralMode(NeutralMode.Brake);
		leftMasterTalon.set(ControlMode.Disabled, 0);
		rightMasterTalon.set(ControlMode.Disabled, 0);
	}
	public void coastTalons(){
		leftMasterTalon.setNeutralMode(NeutralMode.Coast);
		rightMasterTalon.setNeutralMode(NeutralMode.Coast);
	}
	public double getAveragedSpeed(){
		return (Math.abs(leftMasterTalon.getSelectedSensorVelocity()) + Math.abs(rightMasterTalon.getSelectedSensorVelocity()))/2;
	}
}