import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.IOException;

public class JustPractice {

	private static final double dt = 0.005;
	private static final double maxVelocity = 3.0;
	private static final double maxAcceleration = 1.0;
	private static final double maxJerk = 3.0;
	private static final double delta = 0.3048 / 2.0;
	
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, dt, maxVelocity, maxAcceleration, maxJerk);
        Waypoint[] points = new Waypoint[] {	//map out path of robot (x, y) coordinate plane
        		
        		////////PATH ONE///////////
                /*new Waypoint(1.5748, 1.1176, 0),
                new Waypoint(2.77495,1.1176, 0),
                new Waypoint(5.449625,2.2923,0),
                new Waypoint(6.6243,0.7333,Pathfinder.d2r(-90))*/
        		
        		///////PATH TWO//////////
        		/*new Waypoint(1.5748, 1.1176, 0),
                new Waypoint(2.77495,1.1176, 0),
                new Waypoint(5.094478,0.2794,0)*/
        		
        		///////PATH THREE////////
        		/*new Waypoint(1.5748,0,0),
        		new Waypoint(2.77495,0,0),
        		new Waypoint(5.094478,0.2794,0)*/
        		
        		///////PATH FOUR/////////
        		/*new Waypoint(1.5748, 0,0),
        		new Waypoint(2.77495,0,0),
        		new Waypoint(5.094478,-0.2794,0)*/
        		
        		///////PATH FIVE/////////
        		/*new Waypoint(1.5748,-1.1176, 0),
        		new Waypoint(2.77495,-1.1176,0),
        		new Waypoint(5.094478,-0.2794,0)*/
        		
        		///////PATH SIX//////////
                new Waypoint(1.5748, -1.1176, 0),
                new Waypoint(2.77495,-1.1176, 0),
                new Waypoint(5.449625,-2.2923,0),
                new Waypoint(6.6243,-0.7333,Pathfinder.d2r(90))
                
                
        };
        
        //Pathfinder.d2r(degree)

        Trajectory trajectory = Pathfinder.generate(points, config); //create middle trajectory

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(0.75);

        // Do something with the new Trajectories...
        Trajectory left = modifier.getLeftTrajectory();	//Generate left trajectory
        Trajectory right = modifier.getRightTrajectory();	//Generate right trajectory
        
        File leftMotorFile = new File("C://Users//Nathan//Desktop//setpoint//jaciLeftTrajectory.txt");
        Pathfinder.writeToCSV(leftMotorFile, left);		//Create left Motor CSV file
        File rightMotorFile = new File("C://Users//Nathan//Desktop//setpoint//jaciRightTrajectory.txt");
        Pathfinder.writeToCSV(rightMotorFile, right);	//create right motor CSV file
        
        File leftWheelJaci = new File("C://Users//Nathan//Desktop//setpoint//jaciLeftTrajectory.txt");
        Scanner leftScan = new Scanner(leftWheelJaci);	//create left Setpoint Trajectory Graph
        File rightWheelJaci = new File("C://Users//Nathan//Desktop//setpoint//jaciRightTrajectory.txt");
        Scanner rightScan = new Scanner(rightWheelJaci);//create right setpoint trajectory graph
        
        leftScan.nextLine(); //skip format line
        rightScan.nextLine();
        String leftChunk, rightChunk;
        
        File combined = new File("C://Users//Nathan//Desktop//setpoint//jaciFinalTrajectory.txt");
        PrintWriter finalTrajectory = new PrintWriter(combined);
        
        File path = new File("C://Users//Nathan//Desktop//setpoint//robotTrajectoryPath.txt");
        PrintWriter pathVisual = new PrintWriter(path);
        
        double[] leftSetpoint = new double[8];
        double[] rightSetpoint = new double[8];
        double time = 0.0;
        while(leftScan.hasNext()) {
        	leftChunk = leftScan.nextLine();
        	leftSetpoint = stringsToDoubles(leftChunk.split(","));
        	rightChunk = rightScan.nextLine();
        	rightSetpoint = stringsToDoubles(rightChunk.split(","));
        	
        	pathVisual.println(leftSetpoint[1] + "," + leftSetpoint[2]);
        	pathVisual.println(rightSetpoint[1] + "," + rightSetpoint[2]);
        	finalTrajectory.println(time + "," + leftSetpoint[4] + "," + leftSetpoint[5] + "," + rightSetpoint[4] + "," + rightSetpoint[5]);
        	time += dt;
        }
        finalTrajectory.println(time + ",0.00000,0.00000,0.00000,0.00000");
        finalTrajectory.close();
        leftScan.close();
        rightScan.close();
        pathVisual.close();
        
        Path yeet = new Path();
        yeet.addLine(1.2192 + delta);
        //yeet.addLine(0.5 + delta);
        yeet.generatePath();
    }
    
    private static double[] stringsToDoubles(String[] strArray) {
    	double[] result = new double[strArray.length];
    	for(int i = 0; i < strArray.length; i++) {
    		result[i] = Double.parseDouble(strArray[i]);
    	}
    	return result;
    }

}