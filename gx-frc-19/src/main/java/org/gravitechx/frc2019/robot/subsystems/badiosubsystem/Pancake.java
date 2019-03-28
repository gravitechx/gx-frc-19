package org.gravitechx.frc2019.robot.subsystems.badiosubsystem;

import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.PancakeIntakePosition;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pancake {

    private static Pancake pancakeInstance = new Pancake();
    private DoubleSolenoid pancake;
    private PancakeIntakePosition currIntake = PancakeIntakePosition.OUT;
    private PancakeIntakePosition wantedIntake;

    private Pancake() {
        pancake = new DoubleSolenoid(Constants.PANCAKE_SOLENOID_MODULENUMBER, Constants.PANCAKE_SOLENOID_FORWARDCHANNEL, Constants.PANCAKE_SOLENOID_REVERSECHANNEL);
    }

    public static Pancake getPancakeInstance() {
        return pancakeInstance;
    }

    public void setAutonPosition(ArmJoystickMap.PancakeIntakePosition pancakeIntakePosition) {
        ArmControlScheme.getControlSchemeInstance().getArmJoystickMap().pancakeIntakePosition = pancakeIntakePosition;
    }

    public void pancakeAction(ArmJoystickMap.PancakeIntakePosition pancakeIntakePosition) {
        wantedIntake = pancakeIntakePosition;
        if (currIntake != wantedIntake) {
            switch (pancakeIntakePosition) {
                case OUT: 
                    pancake.set(Constants.PANCAKE_OUT);
                    currIntake = PancakeIntakePosition.OUT;
                case IN:
                    pancake.set(Constants.PANCAKE_IN);
                    currIntake = PancakeIntakePosition.IN;
            }
        }
    }

}
