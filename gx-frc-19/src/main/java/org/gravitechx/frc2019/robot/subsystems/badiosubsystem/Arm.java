package org.gravitechx.frc2019.robot.subsystems.badiosubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.*;
import org.gravitechx.frc2019.utils.armutilities.MotorSignal;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Arm {
    private static Arm armInstance = new Arm();
    private Vacuum vacuum;
    private Pancake pancake;
    private BallHolderState stateBH;
    private BallHolder armBH;

    public static class BallHolder {
        
        private TalonSRX armBH = new TalonSRX(Constants.ARM_TALON_PORT);
        private WPI_VictorSPX rightArmBIO = new WPI_VictorSPX(Constants.ARM_RIGHT_BIO_VICTOR_PORT);
        private WPI_VictorSPX leftArmBIO = new WPI_VictorSPX(Constants.ARM_LEFT_BIO_VICTOR_PORT);
        private Solenoid gripperSolenoid = new Solenoid(Constants.GRIPPER_SOLENOID_MODULENUMBER, Constants.GRIPPER_SOLENOID_CHANNEL);
       

        public BallHolder() {
            armBH.config_kP(0, .027);  //.025
            armBH.config_kI(0, 0);
            armBH.config_kD(0, .9);      //1
            armBH.config_kF(0, 0);
            armBH.configAllowableClosedloopError(0, Constants.ARM_PID_ERROR);
            armBH.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        }

        public void setArmBHPositionPID(ButtonArmPosition buttonArmPosition, BallHolderState stateBH) {
            switch(buttonArmPosition) {
                case BALL:
                    armBH.set(ControlMode.Position, findTicks(Constants.BALL_HEIGHT_M), DemandType.ArbitraryFeedForward, stateBH.steadyStateVoltage);
                    break;
                case CARGOBAY:
                    //THE ANGLE NEEDED TO SHOOT THE BALL INTO THE SHUTTLE 
                    armBH.set(ControlMode.Position, findTicks(Constants.SHUTTLE_HEIGHT_M), DemandType.ArbitraryFeedForward, stateBH.steadyStateVoltage);
                    break;
                case ROCKET:
                    armBH.set(ControlMode.Position, findTicks(Constants.ROCKET_HEIGHT_M), DemandType.ArbitraryFeedForward, stateBH.steadyStateVoltage);
                    break;
                case SHUTTLE:
                    armBH.set(ControlMode.Position, findTicks(Constants.SHUTTLE_HEIGHT_M), DemandType.ArbitraryFeedForward, stateBH.steadyStateVoltage);
                    break;
            }
        }
        
        public Solenoid getGripperSolenoid() {
            return gripperSolenoid;
        }

        public WPI_VictorSPX getRightBIO() {
            return rightArmBIO;
        }

        public WPI_VictorSPX getLeftBIO() {
            return leftArmBIO;
        }

        public TalonSRX getArmBH() {
            return armBH;
        }

        public void setBIOState(IntakeState intakeState) {
            switch(intakeState) {
                case INHALE:
                    rightArmBIO.set(ControlMode.PercentOutput, Constants.ARM_BIO_INHALE_SPEED);
                    leftArmBIO.set(ControlMode.PercentOutput, -Constants.ARM_BIO_INHALE_SPEED);
                    break;
                case NEUTRAL:
                    rightArmBIO.set(ControlMode.PercentOutput, 0.0);
                    leftArmBIO.set(ControlMode.PercentOutput, 0.0);
                    break;
                case EXHALE:
                    rightArmBIO.set(ControlMode.PercentOutput, Constants.ARM_BIO_EXHALE_SPEED);
                    leftArmBIO.set(ControlMode.PercentOutput, -Constants.ARM_BIO_EXHALE_SPEED);
                    break;
            }
        }
    }

    public static class BallHolderState {
        public double encoder_ticks = 0.0;
        public double radians = 0.0;
        public double steadyStateVoltage = 0.0;
        OperatingState operating = OperatingState.OPENLOOP;
        public VacuumPosition wantedState;
        public VacuumPosition currState = VacuumPosition.UP;
        public boolean gripperState = false;

        public double setPoint = 0;

        public enum OperatingState {
            OPENLOOP, CLOSEDLOOP;
        }
    }
    
    private Arm() {
        vacuum = Vacuum.getVacuumInstance(); 
        pancake = Pancake.getPancakeInstance();
        armBH = new BallHolder();
        stateBH = new BallHolderState();
        this.zeroEncoder(Constants.ZERO_RADIAN_ENCODER);
        armBH.getArmBH().setSensorPhase(true);
    }

    public static Arm getArmInstance() {
        return armInstance;
    }

    // Simple quick motor test
    //public void setMotor(MotorSignal motorSignal) {
        //armBH.getRightBIO().set(ControlMode.PercentOutput, motorSignal.getOutput());
        //armBH.getLeftBIO().set(ControlMode.PercentOutput, motorSignal.getOutput());
        //armBH.getArmBH().set(ControlMode.PercentOutput, motorSignal.getOutput());
    //}



    //AUTON METHODS
    public void setAutonPosition(ButtonArmPosition buttonArmPosition) {
        ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().armControlType = ArmControlType.BUTTONS;
        ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().buttonArmPosition = buttonArmPosition;
    }

    //THERE IS NO TIME LIMIT TO THE INTAKE STATE, SO IT WILL KEEP INHALING/EXHALING UNTIL TOLD NOT TOO
    //THIS RUNS AUTOMATIC INTAKE STATE, SO IF SET TO INHALE, BOTH VACUUM AND BIO WILL INHALE
    //WHILE IF SET TO EXHALE, ONLY BIO AND NOT THE VACUUM WILL BE SET TO EXHALE
    public void setAutonIntakeState(IntakeState intakeState) {
        ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().automaticBIO = intakeState;
    }

    public void setAutonPancakePosition(PancakeIntakePosition position) {
        pancake.setAutonPosition(position);
    }

    public void setAutonVacuumPosition(VacuumPosition position) {
        vacuum.setAutonSolenoid(position);
    }





    //ENCODER STUFF    
    private static double findRadians(double achievableHeight) {
        return Math.acos(1 - ((achievableHeight - Constants.CHASIS_HEIGHT_M)/Constants.ARM_RADIUS_M)) - (Math.PI / 2);
    }

    public static double findTicks(double achievableHeight) {
        return findRadians(achievableHeight) / Constants.RADIANS_PER_TICK;
    }

    public double getPositionRad() {
        return stateBH.radians;
    }

    public double getPositionTicks() {
        return stateBH.encoder_ticks;
    }

    public void zeroEncoder(double zeroRadians) {
        armBH.getArmBH().setSelectedSensorPosition((int) (zeroRadians / Constants.RADIANS_PER_TICK));
    }

    public void armPerception() {
        stateBH.encoder_ticks = armBH.getArmBH().getSelectedSensorPosition();
        stateBH.radians = stateBH.encoder_ticks * Constants.RADIANS_PER_TICK;
        stateBH.steadyStateVoltage = Constants.STEADY_STATE_VOLTAGE * Math.cos(stateBH.radians);
    }

    

    //Execute method
    public void armAction(ArmJoystickMap armJoystickMap) {

        pancake.pancakeAction(armJoystickMap.pancakeIntakePosition);

        stateBH.wantedState = armJoystickMap.vacuumPosition;
    
        if (stateBH.gripperState == false) {
            armBH.getGripperSolenoid().set(true);
            stateBH.gripperState = true;
        }

        switch(armJoystickMap.vacuumPosition) {
            case DOWN:
                if (stateBH.currState != stateBH.wantedState) {
                    //if (stateBH.encoder_ticks < findTicks(Constants.BALL_HEIGHT_M) + Constants.ARM_PID_ERROR && stateBH.encoder_ticks > findTicks(Constants.BALL_HEIGHT_M) - Constants.ARM_PID_ERROR) {
                        vacuum.setSolenoid(Value.kReverse);
                        stateBH.currState = VacuumPosition.DOWN;
                    //}
                }
                //Controls Arm Position
                switch(armJoystickMap.armControlType) {
                    case MANUAL:
                        armBH.getArmBH().set(ControlMode.PercentOutput, new MotorSignal
                            (armJoystickMap.manualArmPosition.getOutput() * armJoystickMap.manualArmSensitivity).getOutput(),
                                DemandType.ArbitraryFeedForward, (stateBH.steadyStateVoltage));
                        break;
                    case BUTTONS:
                        armBH.setArmBHPositionPID(armJoystickMap.buttonArmPosition, stateBH);
                }

                //Controls Manual Holder Intake State
                armBH.setBIOState(armJoystickMap.manualHolderBIO);
                
                //Controls Manual Vacuum Holder Intake State
                //Vacuum.setVacuumBIO(armJoystickMap.manualVacuumBIO);
                
                //Controls Intake State
                if (armJoystickMap.manualHolderBIO == IntakeState.NEUTRAL && armJoystickMap.manualVacuumBIO == IntakeState.NEUTRAL) {
                    switch (armJoystickMap.automaticBIO) {
                        case INHALE:
                            //vacuum.setVacuumBIO(IntakeState.INHALE);
                            armBH.setBIOState(IntakeState.INHALE);
                            break;
                        case NEUTRAL: 
                            //vacuum.setVacuumBIO(IntakeState.NEUTRAL);
                            armBH.setBIOState(IntakeState.NEUTRAL);
                            break;
                        case EXHALE:
                            //vacuum.setVacuumBIO(IntakeState.NEUTRAL);
                            armBH.setBIOState(IntakeState.EXHALE);
                            break;
                    }
                }
                break;

            case UP:
                //IMPORTANT: ROBOT'S STARTING POSITION MUST ALWAYS BE ARM DOWN, VACUUM DOWN
                armBH.setArmBHPositionPID(ButtonArmPosition.BALL, stateBH);
                //MAKE SURE ARM IS BACK IN BALL POSITION BEFORE BRINGING VACUUM UP
                if (stateBH.currState != stateBH.wantedState) {
                    //if (stateBH.encoder_ticks < findTicks(Constants.BALL_HEIGHT_M) + Constants.ARM_PID_ERROR && stateBH.encoder_ticks > findTicks(Constants.BALL_HEIGHT_M) - Constants.ARM_PID_ERROR) {
                        vacuum.setSolenoid(Value.kForward);
                        stateBH.currState = VacuumPosition.UP;
                    //}
                }

                //Controls Manual Holder Intake State
                armBH.setBIOState(armJoystickMap.manualHolderBIO);

                //Controls Manual Vacuum Holder Intake State
                //vacuum.setVacuumBIO(armJoystickMap.manualVacuumBIO);
                break;

        }

        /*
        System.out.println("\n\nArm Control = " + armJoystickMap.armControlType);
        System.out.println("Vacuum Position = " + armJoystickMap.vacuumPosition);
        System.out.println("Manual Arm Position = " + new MotorSignal(armJoystickMap.manualArmPosition.getOutput() * armJoystickMap.manualArmSensitivity).getOutput());
        System.out.println("Button Arm Position = " + armJoystickMap.buttonArmPosition);
        System.out.println("Manual Holder BIO = " + armJoystickMap.manualHolderBIO);
        System.out.println("Manual Vacuum BIO = " + armJoystickMap.manualVacuumBIO);
        System.out.println("Automatic BIO = " + armJoystickMap.automaticBIO);
        */
    }

}
