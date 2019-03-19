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
	
	private VictorSPX leftSlaveOne;
	private VictorSPX leftSlaveTwo;
	private VictorSPX rightSlaveOne;
	private VictorSPX rightSlaveTwo;

	private Drive() {
		leftMasterTalon = new TalonSRX(Constants.LEFT_MASTER_TALON_PORT);
		rightMasterTalon = new TalonSRX(Constants.RIGHT_MASTER_TALON_PORT);

		leftSlaveOne = new VictorSPX(Constants.LEFT_SLAVE_VICTOR_PORT_ONE);
		leftSlaveTwo = new VictorSPX(Constants.LEFT_SLAVE_VICTOR_PORT_TWO);
		rightSlaveOne = new VictorSPX(Constants.RIGHT_SLAVE_VICTOR_PORT_ONE);
		rightSlaveTwo = new VictorSPX(Constants.RIGHT_SLAVE_VICTOR_PORT_TWO);
		
		leftMasterTalon = DriveTalonSRX.configure(leftMasterTalon);
		rightMasterTalon = DriveTalonSRX.configure(rightMasterTalon);
		rightMasterTalon.setInverted(true);
		rightSlaveOne.setInverted(true);
		rightSlaveTwo.setInverted(true);

		leftSlaveOne.follow(leftMasterTalon);
		leftSlaveTwo.follow(leftMasterTalon);
		rightSlaveOne.follow(rightMasterTalon);
		rightSlaveTwo.follow(rightMasterTalon);
	}

	/*public double getVelocity(){
		return rightMasterTalon.getSelectedSensorVelocity(0)*10.0/4096.0*2.0*Math.PI*2.54/100.0;
	}*/
	
	public void set(DifferentialDriveSignal diffSignal) {
		diffSignal.limitValues();
		leftMasterTalon.set(ControlMode.Velocity, diffSignal.getLeftSide() * 3600);
		rightMasterTalon.set(ControlMode.Velocity, diffSignal.getRightSide() * 3600);
		System.out.println("TICKS: " + leftMasterTalon.getSelectedSensorVelocity(0));
		//System.out.println("CalculatedTurningValue: " + (360 * ((diffSignal.getRightSide() * Constants.maximumTickSpeed * Constants.ticksToMetersConversionFactor) - (diffSignal.getLeftSide() * Constants.maximumTickSpeed * Constants.ticksToMetersConversionFactor))/Constants.distanceBetweenWheelsMeters));
		//leftMasterTalon.set(ControlMode.PercentOutput, 0.1);
		//rightMasterTalon.set(ControlMode.PercentOutput, 0.1);
	}
	public void set(int tickSpeed){
		System.out.println("ERROR: " + leftMasterTalon.getClosedLoopError(0));
		System.out.println("TICKS: " + leftMasterTalon.getSelectedSensorVelocity(0));
		leftMasterTalon.set(ControlMode.Velocity, tickSpeed);
		rightMasterTalon.set(ControlMode.Velocity, tickSpeed);
	}
	public void set(int leftTicks, int rightTicks){
		leftMasterTalon.set(ControlMode.Velocity, leftTicks);
		rightMasterTalon.set(ControlMode.Velocity, rightTicks);
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