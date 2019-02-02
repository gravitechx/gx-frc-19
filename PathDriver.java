package app;
import java.util.Arrays;

public class PathDriver{
    public static void main(String[] args){
            
        Path deepSpacePath = new Path();
        deepSpacePath.addLine(10);
        deepSpacePath.generatePath();
        for(int i = 0; i < 100; i++) {System.out.println(Arrays.toString(deepSpacePath.update()));}
    }
}