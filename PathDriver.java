package app;
import java.io.*;

public class PathDriver{
    public static void main(String[] args) throws InterruptedException, IOException {
            
        Path deepSpacePath = new Path();
        deepSpacePath.addLine(100);
        deepSpacePath.generatePath();
        //for(int i = 0; i < 100; i++) {System.out.println(Arrays.toString(deepSpacePath.update()));}
    }
}