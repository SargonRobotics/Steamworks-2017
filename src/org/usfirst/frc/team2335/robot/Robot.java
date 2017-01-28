package org.usfirst.frc.team2335.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2335.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2335.robot.subsystems.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
	
public class Robot extends IterativeRobot
{	
	//Constants:			
	public static final double DEADZONE = 0.2; //This value is to be edited for best for
	//TODO: Write a more descriptive comment? I don't get the idea at first
	//		glance.
	
	//Axes:
	public static final int MOVE = 1, ROTATE = 2, STRAFE = 0;
	
	//Buttons
	public static final int CENTER = 2;
	
	//Motor ports:
	public static final int LEFT_PORT = 0, RIGHT_PORT = 1, STRAFE_PORT = 2;
	
	//TODO: Remove extra newline.
	//Subsystems:
	public static DriveTrain driveTrain;
	public static Vision vision;
	public static OI oi;

	//Relay
	public static final int RELAY_PORT = 1;
	Relay cameraLight;
	
	//Camera
	//TODO: Read these off of the UsbCamera object after basic functionality.
	//		Exclude CAMERA_ANGLE; we need to define that ourselves anyway.
	public static final int IMG_WIDTH = 640, IMG_HEIGHT = 480;
		
	//Values tape
	public static int centerX = 0, targetWidthPx = 0;
	
	//Camera
	private VisionThread visionThread;
	private Object imgLock = new Object();
	
	//Auto:
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	
	@Override
	public void robotInit() //Runs once to initialize all global variables
	{
		//TODO: Possibly move the camera init to its own private function,
		//		since it's longer and more complex.		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		//Currect view: 11ft
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline ->
		{
			if(!pipeline.filterContoursOutput().isEmpty())
			{
				Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
								
				synchronized (imgLock)
				{
					//TODO: Add explanation of relevant math here, or at least
					//		a link to where it comes from online.
					
					centerX = r1.x + (r1.width / 2);
					targetWidthPx = r1.width;
					
					//TODO: Double-check the math as written on the following:
					//http://wpilib.screenstepslive.com/s/4485/m/24194/l/682952-2017-vision-examples
					//The following assumes that (r.x, r.y) is the top-left corner.
					/*
					double r1Bottom = r1.y-r1.height;
					double r1Right = r1.x+r1.width;
					
					double r2Bottom = r2.y-r1.height;
					double r2Right = r2.x+r2.width;
					
					double groupHeight = r1.height / ((r2Bottom - r1.y) * .4);
					double dTop = (r2.y - r1.y) / ((r2Bottom - r1.y) * .6);
					double lEdge = ((r1.x - r2.x) / r1.width) + 1;
					double widthRatio = r1.width / r2.width;
					double heightRatio = r1.height / (r2.height * 2);
					
					double widthScore = 100 - (100*Math.abs(1 - widthRatio));
					double heightScore = 100 - (100*Math.abs(1 - widthRatio));
					
					 */
					/*
					 * In the formulas below, 1 followed by a letter refers to a coordinate of the bounding 
					 * rect of the first contour, which is the larger of the two (e.g. 1L = 1st contour left e
					 * dge) and 2 with a letter is the 2nd contour. (H=Height, L = Left, T = Top, B = Bottom, 
					 * W = Width)

    Group Height = 1H /((2B - 1T) *.4) Top height should be 40% of total height (4in / 10 in.)
    dTop = (2T - 1T) /((2B - 1T) * .6) Top of bottom stripe to top of top stripe should be 60% of total height 
    (6in / 10 in.)
    LEdge = ((1L - 2L) / 1W) + 1       The distance between the left edge of contour 1 and the left edge of 
    contour 2 should be small relative to the width of the 1st contour (then we add 1 to make the ratio centered 
    on 1
    Width ratio = 1W / 2W   The widths of both contours should be about the same
    Height ratio = 1H / (2H * 2)   The larger stripe should be twice as tall as the smaller one

	Each of these ratios is then turned into a 0-100 score by calculating: 100 - (100 * abs (1 - Val))

	3.   To determine distance, measure pixels from top of top bounding box to bottom of bottom bounding box

	distance = Target height in ft. (10/12) * YRes / (2*PixelHeight*tan(viewAngle of camera)) */
				}
			}
		});
		
		visionThread.start();
		
		driveTrain = new DriveTrain();
		vision = new Vision();
		oi = new OI(); //Initialize OI last or else your code will crash
		
		cameraLight = new Relay(RELAY_PORT);
		
		//chooser.addDefault("Default Auto", new FindTape());		
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	@Override
	public void disabledInit() //Called when robot enters disabled mode. Used for retting any values.
	{

	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	@Override
	public void autonomousInit()
	{
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when teleop starts running.
		if (autonomousCommand != null)
		{
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() //This function is called periodically during operator control
	{
		SmartDashboard.putString("DB/String 0", Double.toString(centerX));
    	SmartDashboard.putString("DB/String 1", Double.toString(targetWidthPx));
    	SmartDashboard.putString("DB/String 2", Double.toString(vision.getDistance()));
    	
    	//Sees if button on the dashboard labled "New Button" (stupid name I know) is pressed
    	if(SmartDashboard.getBoolean("DB/Button 0", false))
    	{
    		//If so, then it turns the light on
    		//Use kForward instead of kOn because kForward uses GND and +12V, where as kOn uses +12V and -12V
    		cameraLight.set(Relay.Value.kForward);
    	}
    	else
    	{
    		cameraLight.set(Relay.Value.kOff);
    	}
    	
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() //This function is called periodically during test mode
	{
		LiveWindow.run();
	}
}
