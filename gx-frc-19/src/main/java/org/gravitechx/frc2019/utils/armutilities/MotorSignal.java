<<<<<<< HEAD
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.gravitechx.frc2019.utils.armutilities;

/**
 * Add your docs here.
 */
public class MotorSignal {
=======

package org.gravitechx.frc2019.utils.armutilities;

public class MotorSignal {

    private final double DEADBAND = .1;
    private final double MAX_OUTPUT = 1;

    private double motorOutput;

    public MotorSignal(double output) {
        motorOutput = checkOutput(output);
    }

    public double getOutput() {
        return checkOutput() * MAX_OUTPUT;
    }

    public double getOutputFromJoystick() {
        return applyDeadband(checkOutput());
    }

    private double applyDeadband(double motorSignal) {
        if (Math.abs(motorSignal) <= DEADBAND) {
            return 0;
        } else if (motorSignal > DEADBAND) {
            return 1/(1 - DEADBAND) * (motorSignal - DEADBAND);
        } else if (motorSignal < -1 * DEADBAND) {
            return 1/(1 - DEADBAND) * (motorSignal + DEADBAND);
        } else {
            return 0;
        }
    }

    private double checkOutput() {
        return checkOutput(motorOutput);
    }

    private double checkOutput(double output) {
        if (output > 1) {
            return 1;
        } else if (output < -1) {
            return -1;
        } else {
            return output;
        }
    }
>>>>>>> 6a9c4027a8a42fc68d111dc8d179a43ef567d429
}
