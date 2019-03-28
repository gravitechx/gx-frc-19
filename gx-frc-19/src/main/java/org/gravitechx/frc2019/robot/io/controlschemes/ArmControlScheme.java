package org.gravitechx.frc2019.robot.io.controlschemes;

import edu.wpi.first.wpilibj.Joystick;
import org.gravitechx.frc2019.robot.Constants;
import org.gravitechx.frc2019.robot.io.controlschemes.ArmControlScheme.ArmJoystickMap.*;
import org.gravitechx.frc2019.utils.armutilities.MotorSignal;

public class ArmControlScheme {

    private static ArmControlScheme instance = new ArmControlScheme();

    private static Joystick joystick;
    private ArmJoystickMap armJoystickMap;

    public static Joystick getJoystick() {
        return joystick;
    }

    public static ArmControlScheme getControlSchemeInstance() {
        return instance;
    }

    /*
    //BUTTON FOR TOGGLING CONTROL BETWEEN MANUAL + AUTOMATIC
    public boolean toggleArmControl() {
        return joystick.getRawButtonPressed(Constants.TOGGLE_ARM_CONTROL_TYPE_BUTTON);
    }



    //BUTTONS FOR AUTOMATIC ARM POSITIONS
    public boolean goToBallHeight() {
        return joystick.getRawButtonPressed(Constants.BALL_HEIGHT_POSITION_BUTTON);
    }

    public boolean goToShuttleShootingHeight() {
        return joystick.getRawButtonPressed(Constants.SHUTTLE_SHOOTING_HEIGHT_POSITION_BUTTON);
    }

    public boolean goToRocketHeight() {
        return joystick.getRawButtonPressed(Constants.ROCKET_HEIGHT_POSITION_BUTTON);
    }


    
    //BUTTON FOR MANUAL ARM POSITION
    public MotorSignal manualArmPositionJoystick() {
        return new MotorSignal (joystick.getY())
    }
    */


    public static class ArmJoystickMap {

        public Joystick getJoystick() {
            return ArmControlScheme.getJoystick();
        }

        //Arm Control and Arm Position
        public ArmControlType armControlType = ArmControlType.BUTTONS;
        public ButtonArmPosition buttonArmPosition = ButtonArmPosition.BALL;
        public MotorSignal manualArmPosition = new MotorSignal(0);
        public double manualArmSensitivity = 0;
        //Intake States
        public IntakeState automaticBIO = IntakeState.NEUTRAL;
        public IntakeState manualHolderBIO = IntakeState.NEUTRAL;
        public IntakeState manualVacuumBIO = IntakeState.NEUTRAL;
        //Vacuum Position
        public VacuumPosition vacuumPosition = VacuumPosition.UP;
        //Pancake Intake States 
        public PancakeIntakePosition pancakeIntakePosition = PancakeIntakePosition.OUT;

        public enum PancakeIntakePosition {
            OUT, IN;
        }
        public enum VacuumPosition {
            UP, DOWN;
        }
        
        public enum ButtonArmPosition {
            BALL, CARGOBAY, ROCKET, SHUTTLE;
        }

        public enum ArmControlType {
            MANUAL, BUTTONS;
        }

        public enum IntakeState {
            INHALE, NEUTRAL, EXHALE
        }

    }

    private ArmControlScheme() {
        joystick = new Joystick(Constants.ARM_JOYSTICK_PORT);
        armJoystickMap = new ArmJoystickMap();
    }

    public ArmJoystickMap getArmJoystickMap() {
        return armJoystickMap;
    }

    public void updateButtonMap() {

        /**
         * buttonMapAction() updates BUTTONS/JOYSTICKS VALUE
         * If vacuum position is up, arm position cannot be moved to avoid collision. 
         * This also means that arm must be back into ball position to avoid collision.
         * Manual Holder and Vacuum IN/EXhale must take priority over Automatic IN/EXhale
         */

         //Toggle Vacuum Position
         if (joystick.getRawButtonPressed(Constants.TOGGLE_VACUUM_POSITION_BUTTON)) {
            if (armJoystickMap.vacuumPosition == VacuumPosition.DOWN) {
                armJoystickMap.vacuumPosition = VacuumPosition.UP;
            } else {
                armJoystickMap.vacuumPosition = VacuumPosition.DOWN;
            }
        }

        //armJoystickMap.vacuumPosition = VacuumPosition.DOWN;

        //ARM CONTROL
        //Toggle Arm Control Type between Manual and Buttons
        if (joystick.getRawButtonPressed(Constants.TOGGLE_ARM_CONTROL_TYPE_BUTTON)) {
            if (armJoystickMap.armControlType == ArmControlType.MANUAL) {
                armJoystickMap.armControlType = ArmControlType.BUTTONS;
            } else {
                armJoystickMap.armControlType = ArmControlType.MANUAL;
            }
        }

        //PANCAKE CONTROL
        if (joystick.getRawButtonPressed(Constants.TOGGLE_PANCAKE)) {
            if (armJoystickMap.pancakeIntakePosition == PancakeIntakePosition.IN) {
                armJoystickMap.pancakeIntakePosition = PancakeIntakePosition.OUT;
            } else {
                armJoystickMap.pancakeIntakePosition = PancakeIntakePosition.IN;
            }
        }

        //Automatic Arm Sensitivity
        armJoystickMap.manualArmSensitivity = Math.abs((joystick.getThrottle() - 1)/2);
        //Manual Arm Position Control
        armJoystickMap.manualArmPosition = new MotorSignal(joystick.getY());
        
        //Button Arm Position Control
        if (joystick.getRawButtonPressed(Constants.BALL_HEIGHT_POSITION_BUTTON)) {
            armJoystickMap.buttonArmPosition = ButtonArmPosition.BALL;
        } else if (joystick.getRawButtonPressed(Constants.SHUTTLE_SHOOTING_HEIGHT_POSITION_BUTTON)) {
            armJoystickMap.buttonArmPosition = ButtonArmPosition.CARGOBAY;
        } else if (joystick.getRawButtonPressed(Constants.ROCKET_HEIGHT_POSITION_BUTTON)) {
            armJoystickMap.buttonArmPosition = ButtonArmPosition.ROCKET;
        } else if (joystick.getRawButtonPressed(Constants.SHUTTLE_HEIGHT_POSITION_BUTTON)) {
            armJoystickMap.buttonArmPosition = ButtonArmPosition.SHUTTLE;
        }  

        //Manual Holder Intake State
        if (joystick.getRawButton(Constants.MANUAL_HOLDER_INHALE_BUTTON)) {
            armJoystickMap.manualHolderBIO = IntakeState.INHALE;
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        } else if (joystick.getRawButton(Constants.MANUAL_HOLDER_EXHALE_BUTTON)) {
            armJoystickMap.manualHolderBIO = IntakeState.EXHALE;
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        } else {
            armJoystickMap.manualHolderBIO = IntakeState.NEUTRAL;
        }

        //Manual Vacuum Intake State
        if (joystick.getRawButton(Constants.MANUAL_VACUUM_INHALE_BUTTON)) {
            armJoystickMap.manualVacuumBIO = IntakeState.INHALE;
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        } else if (joystick.getRawButton(Constants.MANUAL_VACUUM_EXHALE_BUTTON)) {
            armJoystickMap.manualVacuumBIO = IntakeState.EXHALE;
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        } else {
            armJoystickMap.manualVacuumBIO = IntakeState.NEUTRAL;
        }

        //Automatic Intake State
        if (joystick.getPOV() == -1) {
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        } else if (joystick.getPOV() < 90 || joystick.getPOV() > 270) {
            armJoystickMap.automaticBIO = IntakeState.EXHALE;
        } else if (joystick.getPOV() > 90 && joystick.getPOV() < 270) {
            armJoystickMap.automaticBIO = IntakeState.INHALE;
        } else {
            armJoystickMap.automaticBIO = IntakeState.NEUTRAL;
        }
    }

}
