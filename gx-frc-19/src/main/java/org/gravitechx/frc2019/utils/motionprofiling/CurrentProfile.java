package org.gravitechx.frc2019.utils.motionprofiling;

import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrentProfile {
    private ArrayList<double[]> leftSide, rightSide;
    private double startTime, timeLength;
    private int arrayIndex;

    public CurrentProfile(){
        arrayIndex = 0;
    }
    public void startTime(){
        startTime = Timer.getFPGATimestamp();
    }
    public double[][] getSetpoints(){
        timeLength = Timer.getFPGATimestamp() - startTime;
        while (leftSide.get(arrayIndex + 1)[0] < timeLength){
            arrayIndex++;
        }
        return new double[][] {Arrays.copyOfRange(leftSide.get(arrayIndex), 1, 3), 
            Arrays.copyOfRange(rightSide.get(arrayIndex), 1, 3)};
    }
    public double getTimeLength(){
        return timeLength;
    }
}