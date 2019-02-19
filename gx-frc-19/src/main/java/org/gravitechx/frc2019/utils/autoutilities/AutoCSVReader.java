package org.gravitechx.frc2019.utils.autoutilities;

import edu.wpi.first.wpilibj.Timer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AutoCSVReader {
    private BufferedReader br;
    private double[] latestSetpoints, nextSetpoints;
    private double initialTime;
    public AutoCSVReader() throws FileNotFoundException, IOException{
        resetReader();
    }
    public double[] getSetpoints() throws IOException{
        String line;
        if(initialTime == 0) {
            initialTime = Timer.getFPGATimestamp();
        }
        if(!Arrays.equals(nextSetpoints,new double[]{})) {//If there's a possiblity that lastestSetpoints needs to be updated
            while(Timer.getFPGATimestamp()-initialTime >= nextSetpoints[0]) {//If it's time to start using nextSetpoints
                latestSetpoints = nextSetpoints;
                if((line = br.readLine()) != null) {//If there's any more lines to read
                    nextSetpoints = stringsToDoubles(line.split(","));
                } else {
                    nextSetpoints = new double[]{};
                }
            }
        }
        System.out.println("TIME: " + (Timer.getFPGATimestamp()-initialTime));
        return Arrays.copyOfRange(latestSetpoints, 1, 5);
    }
    private double[] stringsToDoubles(String[] strArray) {
        double[] result = new double[strArray.length];
        for(int i = 0;i<strArray.length;i++) {
            result[i] = Double.parseDouble(strArray[i]);
        }
        return result;
    }
    public void resetReader() throws FileNotFoundException, IOException{
        br = new BufferedReader(new FileReader("/home/admin/AutoSetpoints.txt"));
        br.readLine();
        latestSetpoints = stringsToDoubles(br.readLine().split(","));
        nextSetpoints = stringsToDoubles(br.readLine().split(","));
        initialTime = 0;
    }
}