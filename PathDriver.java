package app;
import java.io.*;

public class PathDriver{
    public static void main(String[] args) throws InterruptedException, IOException {
        
        Path deepSpacePath = new Path();
        //deepSpacePath.addCurve(5, Math.PI/2.0, 10);
        deepSpacePath.addLine(4);
        //deepSpacePath.addCurve(5, Math.PI/2.0, 10);
        System.out.println(deepSpacePath);
        deepSpacePath.generatePath();
        deepSpacePath.writeToFile("C:\\Users\\FRC\\Desktop\\");
        //for(int i = 0; i < 100; i++) {System.out.println(Arrays.toString(deepSpacePath.update()));}
    }
} 