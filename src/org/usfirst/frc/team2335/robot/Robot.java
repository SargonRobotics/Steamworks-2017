package org.usfirst.frc.team2335.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2335.robot.commands.FindTape;
import org.usfirst.frc.team2335.robot.subsystems.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	
	//Subsystems:
	public static Vision vision;
	public static OI oi;

	//GRIP
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	public static int area = 0, centerX = 0, centerY = 0;
	
	private VisionThread visionThread;
	
	private UsbCamera camera;
	
	private Object imgLock = new Object();
	
	//Auto:
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	
	@Override
	public void robotInit() //Runs once to initialize all global variables
	{
		vision = new Vision();
		oi = new OI(); //Initialize OI last or else your code will crash
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline ->
		{
			if(!pipeline.filterContoursOutput().isEmpty())
			{
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (imgLock)
				{
					area = r.height * r.width;
					centerX = r.x + (r.width / 2);
					centerY = r.y + (r.height / 2);
				}
			}
		});
		visionThread.start();
		
		chooser.addDefault("Default Auto", new FindTape());
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
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() //This function is called periodically during test mode
	{
		LiveWindow.run();
	}
}
