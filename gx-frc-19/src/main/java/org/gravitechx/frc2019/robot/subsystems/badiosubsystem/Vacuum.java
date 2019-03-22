
package org.gravitechx.frc2019.robot.subsystems.badiosubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Vacuum {

    private static Vacuum instance = new Vacuum();

    private DoubleSolenoid vacuumSol;
    //private DoubleSolenoid vacuumRightSol;
    //private DoubleSolenoid vacuumLeftSol;
    private WPI_VictorSPX vacuumBIO;

    private Vacuum() {
        //vacuumSol = new DoubleSolenoid(Constants.VACUUM_SOLENOID_MODULENUMBER, Constants.VACUUM_SOLENOID_FORWARDCHANNEL, Constants.VACUUM_SOLENOID_REVERSECHANNEL);
        //vacuumRightSol = new DoubleSolenoid(Constants.RIGHT_SOLENOID_MODULENUMBER, Constants.RIGHT_SOLENOID_FORWARDCHANNEL, Constants.RIGHT_SOLENOID_REVERSECHANNEL);
        //vacuumLeftSol = new DoubleSolenoid(Constants.LEFT_SOLENOID_MODULENUMBER, Constants.LEFT_SOLENOID_FORWARDCHANNEL, Constants.LEFT_SOLENOID_REVERSECHANNEL);
        //vacuumBIO = new WPI_VictorSPX(Constants.VACUUM_BIO_VICTOR_PORT);
    }

    public static Vacuum getVacuumInstance() {
        return instance;
    }

    public void setSolenoid(DoubleSolenoid.Value position) {
       // vacuumSol.set(position);
        //vacuumRightSol.set(position);
        //vacuumLeftSol.set(position);
    }
    
    public void setVacuumBIO(IntakeState intakeState) {
        switch(intakeState) {
            case INHALE: 
                System.out.println("Vacuum Intake State = INHALE");
                //vacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_INHALE_SPEED);
                break;
            case NEUTRAL:
            System.out.println("Vacuum Intake State = NEUTRAL");
                //vacuumBIO.set(ControlMode.PercentOutput, 0);
                break;
            case EXHALE:
                System.out.println("Vacuum Intake State = EXHALE");
                //vacuumBIO.set(ControlMode.PercentOutput, Constants.VACUUM_EXHALE_SPEED);
                break;
        }
    }

}