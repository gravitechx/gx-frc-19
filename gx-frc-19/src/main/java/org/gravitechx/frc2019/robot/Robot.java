 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.gravitechx.frc2019.robot;

import org.gravitechx.frc2019.utils.driveutilities.RotationalDriveSignal;
import org.gravitechx.frc2019.utils.autoutilities.AutoCSVReader;
import org.gravitechx.frc2019.robot.io.controlschemes.JoystickControlScheme;
import org.gravitechx.frc2019.robot.subsystems.drivesubsystem.Drive;
import org.gravitechx.frc2019.robot.subsystems.drivesubsystem.DrivePipeline;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Timer;
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
		try {
			autoReader.resetReader();
		} catch (Exception e){
			System.out.println("The setpoint reader refused to be reset.");
		}
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
		try {
			autonomousSetpoints = autoReader.getSetpoints();
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
		//System.out.println("RegisteredTurningValue: " + gyro.getRawGyroY());
		//printWriter.append(Timer.getFPGATimestamp() + ", " + drive.getVelocity() + "\n");
	}
	
	@Override
	public void testInit() {
		tinit = Timer.getFPGATimestamp();
		drive.coastTalons();
	}

	/**
	 * This function is called periodically  during test mode.
	 */
	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("Average Speed", drive.getAveragedSpeed());
		/* This is the two meter test
		if (Timer.getFPGATimestamp()-tinit < 1){
			drive.set((int)((Timer.getFPGATimestamp()-tinit) * 855.51049 * 2));
		} else if (Timer.getFPGATimestamp()-tinit < 2) {
			drive.set((int)(((Timer.getFPGATimestamp()-tinit) * -2 + 4) * 855.51049 ));
		} else {
			drive.brakeTalons();
		}*/
		drive.set(1000);
	}
	//8555.1049 ticks in a meter
}
