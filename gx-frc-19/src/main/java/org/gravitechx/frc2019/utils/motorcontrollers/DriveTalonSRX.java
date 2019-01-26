package org.gravitechx.frc2019.utils.motorcontrollers;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.gravitechx.frc2019.robot.Constants;

public class DriveTalonSRX {
    public static TalonSRX configure(TalonSRX talon){
        talon.setNeutralMode(NeutralMode.Brake);
        talon.selectProfileSlot(0, 0);
        talon.setSensorPhase(true);
        talon.configAllowableClosedloopError(0, Constants.allowablePIDError);
        //talon.configSelectedFeedbackSensor();
        //talon.configNominalOutputForward();
        if (Constants.enablePID){
            talon.config_kP(0, Constants.kProportional);
            talon.config_kI(0, Constants.kIntegral);
            talon.config_kD(0, Constants.kDerivative);
            talon.config_kF(0, Constants.kFeedForward);
        } else {
            talon.config_kP(0, 0);
            talon.config_kI(0, 0);
            talon.config_kD(0, 0);
            talon.config_kF(0, 0);
        }
        return talon;
    }
}