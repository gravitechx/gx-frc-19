package org.gravitechx.frc2019.robot.subsystems.badiosubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.*;
import org.gravitechx.frc2019.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Vacuum {

    private static Vacuum instance = new Vacuum();

    private DoubleSolenoid vacuumSol;
    private WPI_VictorSPX leftVacuumBIO;
    private WPI_VictorSPX rightVacuumBIO;

    private Vacuum() {
        vacuumSol = new DoubleSolenoid(Constants.VACUUM_SOLENOID_MODULENUMBER, Constants.VACUUM_SOLENOID_FORWARDCHANNEL, Constants.VACUUM_SOLENOID_REVERSECHANNEL);
        leftVacuumBIO = new WPI_VictorSPX(Constants.LEFT_VACUUM_BIO_VICTOR_PORT);
        rightVacuumBIO = new WPI_VictorSPX(Constants.RIGHT_VACUUM_BIO_VICTOR_PORT);
        rightVacuumBIO.setInverted(true);
    }

    public static Vacuum getVacuumInstance() {
        return instance;
    }

    public void setAutonSolenoid(VacuumPosition position) {
        ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().vacuumPosition = position;
    }

    public void setSolenoid(DoubleSolenoid.Value position) {
        if (position.equals(Value.kReverse)) {
            ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().vacuumPosition = VacuumPosition.DOWN;
        } else if (position.equals(Value.kForward)) {
            ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().vacuumPosition = VacuumPosition.UP;
        }
        vacuumSol.set(position);
    }
    
    public void setVacuumBIO(IntakeState intakeState) {
        switch(intakeState) {
            case INHALE: 
                leftVacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_INHALE_SPEED);
                rightVacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_INHALE_SPEED);
                break;
            case NEUTRAL:
                leftVacuumBIO.set(ControlMode.PercentOutput, 0);
                rightVacuumBIO.set(ControlMode.PercentOutput, 0);
                break;
            case EXHALE:
                leftVacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_EXHALE_SPEED);
                rightVacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_EXHALE_SPEED);
                break;
        }
    }

}