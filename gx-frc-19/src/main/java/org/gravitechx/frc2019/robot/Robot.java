 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.gravitechx.frc2019.robot;

import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.utils.autoutilities.AutoCSVReader;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.JoystickControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.*;
import org.gravitechx.frc2019.robot.subsystems.badiosubsystem.Arm;
import org.gravitechx.frc2019.robot.subsystems.drivesubsystem.Drive;
import org.gravitechx.frc2019.robot.subsystems.drivesubsystem.DrivePipeline;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;

import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	//public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	public JoystickControlScheme driverControls;
	public Drive drive;
	public DrivePipeline pipe;
	public AHRS gyro;
	public AutoCSVReader autoReader;
	Command m_autonomousCommand;
	//SendableChooser<Command> m_chooser = new SendableChooser<>();
	public PrintWriter printWriter;
	PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	double tinit;
	double[] autonomousSetpoints;
	Arm arm;
	ArmControlScheme armControlScheme;
	//Vacuum vacuum = Vacuum.getVacuumInstance();
	//VictorSPX random = new VictorSPX(6);
	//VictorSPX random2 = new VictorSPX(0);

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.print("Initializing robot: ");
		driverControls = JoystickControlScheme.getInstance();
		drive = Drive.getInstance();
		pipe = new DrivePipeline();
		gyro = new AHRS(Port.kMXP);
		arm = Arm.getArmInstance();
		armControlScheme = ArmControlScheme.getControlSchemeInstance();
		try {
			autoReader = new AutoCSVReader();
		} catch (Exception e){
			System.out.print("No setpoints were provided.");
		}
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		try {
			printWriter = new PrintWriter(new FileWriter("/home/lvuser/vlog.txt"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		System.out.println("Robot Disabled.");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//tinit = Timer.getFPGATimestamp();
		/*try {
			autoReader.resetReader();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("The setpoint reader refused to be reset.");
		}*/
		//m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
		//drive.set(pipe.filter(new RotationalDriveSignal(driverControls.getThrottle(), driverControls.getRotation()), driverControls.getLeftSkrtTurn(), driverControls.getRightSkrtTurn()));
		arm.setAutonVacuumPosition(VacuumPosition.DOWN);
		arm.armAction(armControlScheme.getArmJoystickMap());
		arm.armPerception();

		/*
		// AUTONOMOUS METHODS THAT SHOULD WORK FOR ARM AUTON
		
		
		//SET THE VACUUM TO DOWN BEFORE MOVING THE ARM TO AVOID COLLISIONS
		arm.setAutonVacuumPosition(VacuumPosition.DOWN);

		//setAutonPosition and setIntakePosition sets the points, and armAction method will 
		//execute them. armPerception is needed for keeping the arm in it's setPosition.

		//ButtonArmPosition.CARGOBAY is the shooting height for cargo bay (haven't been able to fine-tune yet)
		arm.setAutonPosition(ButtonArmPosition.CARGOBAY);		
		
		arm.setAutonIntakeState(IntakeState.EXHALE);
		arm.setAutonIntakeState(IntakeState.NEUTRAL);
		//No idea if IN is actually holding the disks IN... will test when possible
		//arm.setAutonPancakePosition(PancakeIntakePosition.IN);
		*/



		/*if (Timer.getFPGATimestamp()-tinit < 1.5){
			drive.set(1000);
		} else {
			drive.set(0);
		}*/
		try {
			double[] currents = {pdp.getCurrent(Constants.LEFT_MASTER_TALON_PORT), pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_ONE), pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_TWO), pdp.getCurrent(Constants.RIGHT_MASTER_TALON_PORT), pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_ONE), pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_TWO)};
			autonomousSetpoints = autoReader.getSetpoints(drive.getLeftVelocityTicks(), drive.getRightVelocityTicks(), currents);
		} catch (Exception e){
			System.out.println("Autonomous cannot get setpoints. Periodically");
		}
		drive.set((int)(855.51049 * autonomousSetpoints[0]), (int)(855.51049 * autonomousSetpoints[2]));
	}

	@Override
	public void teleopInit() {
		System.out.print("Initializing Teleop: ");
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		System.out.println("DONE");
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		drive.set(pipe.filter(new RotationalDriveSignal(driverControls.getThrottle(), driverControls.getRotation()), driverControls.getLeftSkrtTurn(), driverControls.getRightSkrtTurn()));
		Scheduler.getInstance().run();
		//System.out.println("running?");
	
		//vacuum.setSolenoid(Value.kForward);
		
		//random.set(ControlMode.PercentOutput, -0.35);
		//random2.set(ControlMode.PercentOutput, -0.35);
		
		arm.armPerception();
    	armControlScheme.updateButtonMap();
		arm.armAction(armControlScheme.getArmJoystickMap());
		
		//System.out.println("RegisteredTurningValue: " + gyro.getRawGyroY());
		//printWriter.append(Timer.getFPGATimestamp() + ", " + drive.getVelocity() + "\n");
	}
	
	@Override
	public void testInit() {
		try {
			autoReader.resetReader();
		} catch (Exception e){
			System.out.println("The setpoint reader refused to be reset.");
		}
	}

	/**
	 * This function is called periodically  during test mode.
	 */
	@Override
	public void testPeriodic() {
		System.out.println("LEFT: " + drive.getLeftVelocityTicks() + " RIGHT: " + drive.getRightVelocityTicks() + " \nLEFT CURRENT: " + ((pdp.getCurrent(Constants.LEFT_MASTER_TALON_PORT) + pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_ONE) + pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_TWO))/3) + " RIGHT CURRENT: " + ((pdp.getCurrent(Constants.RIGHT_MASTER_TALON_PORT) + pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_ONE) + pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_TWO))/3));
		try {
			double[] currents = {pdp.getCurrent(Constants.LEFT_MASTER_TALON_PORT), pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_ONE), pdp.getCurrent(Constants.LEFT_SLAVE_VICTOR_PORT_TWO), pdp.getCurrent(Constants.RIGHT_MASTER_TALON_PORT), pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_ONE), pdp.getCurrent(Constants.RIGHT_SLAVE_VICTOR_PORT_TWO)};
			autonomousSetpoints = autoReader.getSetpoints(drive.getLeftVelocityTicks(), drive.getRightVelocityTicks(), currents);
		} catch (Exception e){
			System.out.println("Autonomous cannot get setpoints. Periodically");
		}
		drive.set((int)(855.51049 * autonomousSetpoints[0]), (int)(855.51049 * autonomousSetpoints[2]));
	}
	//8555.1049 ticks in a meter
}
