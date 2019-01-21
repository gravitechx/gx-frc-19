package org.gravitechx.frc2019.utils.controllers;

import edu.wpi.first.wpilibj.Timer;

public class PIDFController{
    private final double kVelocity, kAcceleration, kProportional, kIntegral, kDerivative;
    private double lastTime = Double.NaN, currentTime, accumulatedError = 0, error = Double.NaN, correction = 0, lastError = Double.NaN, dt = Double.NaN;

    public PIDFController(double kVelocity, double kAcceleration, double kProportional, double kIntegral, double kDerivative){
        this.kVelocity = kVelocity;
        this.kAcceleration = kAcceleration;
        this.kProportional = kProportional;
        this.kIntegral = kIntegral;
        this.kDerivative = kDerivative; 
        currentTime = Timer.getFPGATimestamp();
    }

    public double apply(double realVelocity, double setpointVelocity, double setpointAcceleration){
        correction = 0;
        lastTime = currentTime;
        currentTime = Timer.getFPGATimestamp();
        dt = currentTime - lastTime;
        error = setpointVelocity - realVelocity;
        accumulatedError += error;

        if (!Double.isNaN(lastError)){
            correction = (kProportional * error) + (kIntegral * accumulatedError)
                 + (((error - lastError)/dt) * kDerivative);
        }   

        lastError = error;

        return (setpointVelocity * kVelocity) + (setpointAcceleration * kAcceleration) + correction;
    }
}