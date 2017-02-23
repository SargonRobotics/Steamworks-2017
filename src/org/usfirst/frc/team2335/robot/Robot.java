package org.usfirst.frc.team2335.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2335.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2335.robot.subsystems.Ultrasound;
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
	//0.2 is the value that everything before that is zeroed out
	//So about 20% of the input will be zeroed out
	//This created a small zone where no input will affect the output: a "deadzone"
	public static final double DEADZONE = 0.2;
	
	//Axes:
	public static final int MOVE = 1, ROTATE = 2, STRAFE = 0;
	
	//Buttons
	public static final int CENTER = 2, POSITION = 3;
	
	//Motor ports:
	public static final int LEFT_PORT = 0, RIGHT_PORT = 1, STRAFE_PORT = 2;
	
	//Ultrasonic ports:
	public static final int BACK_ECHO = 0, BACK_PING = 1, FRONT_ECHO = 3, FRONT_PING = 4;

	//TODO: Remove extra newline.
	//TODO: Remove unnecessary TODO comment
	
	//Subsystems:
	public static DriveTrain driveTrain;
	public static Vision vision;
	public static Ultrasound ultraSound;
	public static OperatorInterface oi;

	//Relay
	public static final int RELAY_PORT = 1;
	Relay cameraLight;
	
	//Camera
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
		driveTrain = new DriveTrain();
		ultraSound = new Ultrasound();
		vision = new Vision();
		
		//This one comes last or else your code dies just like you if you don't define it last
		oi = new OperatorInterface();
		
		initCamera();
		
		//TODO: Get auto chooser working
		//chooser.addDefault("Default Auto", new FindTape());		
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}
	
	private void initCamera()
	{
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setFPS(30);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline ->
		{
			if(!pipeline.filterContoursOutput().isEmpty())
			{
				Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
								
				synchronized (imgLock)
				{
					//centerX value will be the x coordinate in pixels for the exact center point of the tape
					//r1.x is the left x coordinate value for the tape
					//and r1.width is how many pixels wide the tape is seen
					//so to find the center it finds the edge, and adds half the width
					centerX = r1.x + (r1.width / 2);
					
					//targetWidthPx is what width our reflective tape is, used in math for distance
					targetWidthPx = r1.width;
				}
			}
		});
		
		//Please for the love of god don't forget this line or else nothing works and I lose will to live
		visionThread.start();
		
		cameraLight = new Relay(RELAY_PORT);

	}

	@Override
	public void disabledInit() //Called when robot enters disabled mode. Used for reseting any values.
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
		
		SmartDashboard.putString("DB/String 0", Double.toString(ultraSound.getRangeBack()));
	}

	@Override
	public void teleopPeriodic() //This function is called periodically during operator control	
	{
		SmartDashboard.putString("DB/String 0", Double.toString(ultraSound.getRangeBack()));
		SmartDashboard.putString("DB/String 1", Double.toString(ultraSound.getRangeFront()));
	
    	driveTrain.drive(oi.getAxis(MOVE, 1), oi.getAxis(ROTATE, 0.6));

    	
    	//Sees if button on the dashboard labeled "New Button" (stupid name I know) is pressed
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
