package org.usfirst.frc.team2335.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2335.robot.commands.AutoFromStation1;
import org.usfirst.frc.team2335.robot.commands.AutoFromStation2;
import org.usfirst.frc.team2335.robot.commands.AutoFromStation3;
import org.usfirst.frc.team2335.robot.commands.AutoSraightGroup;
import org.usfirst.frc.team2335.robot.subsystems.BackUltraPID;
import org.usfirst.frc.team2335.robot.subsystems.Climb;
import org.usfirst.frc.team2335.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2335.robot.subsystems.FrontUltraPID;
import org.usfirst.frc.team2335.robot.subsystems.GyroPID;
import org.usfirst.frc.team2335.robot.subsystems.Shooter;
import org.usfirst.frc.team2335.robot.subsystems.TurnCorrectionPID;
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
	public static final int MOVE = 1, ROTATE = 0;
	
	//Climb buttons
	public static final int CLIMB = 2;
	
	//Shooter buttons
	public static final int SHOOT_BUTTON = 3 /* <-- AXES */, INTAKE_BUTTON = 1;
	
	//Vision buttons
	public static final int CENTER = 3, POSITION = 4;
	
	//Move buttons
	public static final int STRAFELEFT = 5, STRAFERIGHT = 6;
	
	//Motor ports:
	public static final int LEFT_PORT = 0, RIGHT_PORT = 1, STRAFE_PORT = 2,
			SHOOTER_MOTOR = 4, INTAKE_MOTOR = 3, FEEDER_MOTOR = 5,
			CLIMB_PORT = 6;
	
	//Ultrasonic ports:
	public static final int BACK_ECHO = 8, BACK_PING = 9, FRONT_ECHO = 3, FRONT_PING = 4;
	
	public static final int JOYSTICK = 0, XBOX = 1;

	//TODO: Remove extra newline.
	//TODO: Remove unnecessary TODO comment
	
	//Subsystems:
	public static Climb climb;
	public static DriveTrain driveTrain;
	public static GyroPID gyroPID;
	public static TurnCorrectionPID turnCorrectionPID;
	public static Shooter shooter;
	public static Vision vision;
	public static Ultrasound ultrasound;
	public static FrontUltraPID frontUltraPID;
	public static BackUltraPID backUltraPID;
	public static OperatorInterface oi;

	//Relay
	public static final int RELAY_PORT = 1;
	Relay cameraLight;
	
	//Camera
	public static final int IMG_WIDTH = 640, IMG_HEIGHT = 480;
		
	//Values tape
	public static int centerX = 0, targetWidthPx = 0;
	
	//For shoot logic
	public boolean isShooting = false;
	
	//Camera
	private VisionThread visionThread;
	private Object imgLock = new Object();
	
	//Auto:
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	
	@Override
	public void robotInit() //Runs once to initialize all global variables
	{
		//Initializes all the subsystems
		driveTrain = new DriveTrain();
		climb = new Climb();
		gyroPID = new GyroPID();
		turnCorrectionPID = new TurnCorrectionPID();
		shooter = new Shooter();
		ultrasound = new Ultrasound();
		frontUltraPID = new FrontUltraPID();
		backUltraPID = new BackUltraPID();
		vision = new Vision();
		
		//This one comes last or else your code dies just like you if you don't define it last
		oi = new OperatorInterface();
				
		initCamera();

		//Adds autonomous choosers
		chooser.addDefault("Straight", new AutoSraightGroup());
		chooser.addObject("Left", new AutoFromStation1());
		chooser.addObject("Center", new AutoFromStation2());
		chooser.addObject("Right", new AutoFromStation3());
		
		SmartDashboard.putData("Auto Command:", chooser);
	}
		
	private void initCamera()
	{
		//Adds the camera, and sets the FPS
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setFPS(30);
		
		//Initializes the relay for the light
		cameraLight = new Relay(RELAY_PORT);
		
		//Starts a thread running out vision code
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline ->
		{
			//Sees if there's any contours (reflective tape)
			if(!pipeline.filterContoursOutput().isEmpty())
			{
				//If there is, sets it to a rect object
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
	}

	@Override
	public void disabledInit() //Called when robot enters disabled mode. Used for reseting any values.
	{
    	cameraLight.set(Relay.Value.kOff);
    	gyroPID.disable();
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
		//autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		gyroPID.reset();
		autonomousCommand = (Command) chooser.getSelected();
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic()
	{
		SmartDashboard.putString("Front Range:", Double.toString(ultrasound.getBackRange()));	
		SmartDashboard.putString("Back Range:", Double.toString(ultrasound.getBackRange()));	
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

		gyroPID.reset();
		gyroPID.enable();
	}

	@Override
	public void teleopPeriodic() //This function is called periodically during operator control	
	{
//		SmartDashboard.putString("Center:", Double.toString(centerX));
//		SmartDashboard.putString("Back Range:", Double.toString(ultrasound.getBackRange()));
//		SmartDashboard.putString("Front Range:", Double.toString(ultrasound.getFrontRange()));	
//		SmartDashboard.putString("Gyro:", Double.toString(gyroPID.getAngle()));
		
    	driveTrain.drive(oi.getAxis(MOVE, 1), oi.getAxis(ROTATE, 0.8));
    	
    	//Decreases or increases the speed of the shooter motor
    	//If the d-pad buttons are pressed on the xbox controller
    	if(oi.xbox.getPOV() == 0 && shooter.motorSpeed > -1)
    	{
    		if(shooter.motorSpeed - 0.05 < -1)
    		{
    			shooter.motorSpeed = -1;
    		}
    		else
    		{
    			shooter.speedUp();
    		}
    	}
    	else if(oi.xbox.getPOV() == 180 && shooter.motorSpeed < 0)
    	{
    		if(shooter.motorSpeed + 0.05 < 0)
    		{
    			shooter.motorSpeed = 0;
    		}
    		else
    		{
    			shooter.speedDown();
    		}
    	}
    	
    	
    	//TODO: Fix this logic
    	/*
    	if(DriverStation.getInstance().getMatchTime() <= 20 &&
    			DriverStation.getInstance().getMatchTime() >= 18)
    	{
    		oi.xbox.setRumble(RumbleType.kLeftRumble, 1);
    		oi.xbox.setRumble(RumbleType.kRightRumble, 1);    		
    	}
    	else if(DriverStation.getInstance().getMatchTime() == 17)
    	{
    		oi.xbox.setRumble(RumbleType.kLeftRumble, 0);
    		oi.xbox.setRumble(RumbleType.kRightRumble, 0);    
    	}
    	
    	SmartDashboard.putString("DB/String 9" , Double.toString(DriverStation.getInstance().getMatchTime()));
    	*/
    	
    	//cameraLight.set(Relay.Value.kForward);

    	
    	Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() //This function is called periodically during test mode
	{
		LiveWindow.run();
	}
}
