package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2335.robot.subsystems.Ultrasound;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot
{	
	//Constants:
	public static final double DEADZONE = 0.2; //This value is to be edited for best fir
	
	//Axes:
	public static final int MOVE = 1, ROTATE = 2, STRAFE = 0;
	
	//Motor ports:
	public static final int LEFT_PORT = 0, RIGHT_PORT = 1, STRAFE_PORT = 2;
	
	//Ultrasonic ports:
	public static final int ECHO_PIN = 0, PULSE_PIN = 1;
	
	//Subsystems:
	public static DriveTrain driveTrain;
	public static Ultrasound ultraSound;
	public static OI oi;

	//Auto:
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser();

	
	@Override
	public void robotInit() //Runs once to initialize all global variables
	{
		driveTrain = new DriveTrain();
		ultraSound = new Ultrasound();
		oi = new OI(); //Initialize OI last or else your code will crash
		
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
		SmartDashboard.putString("DB/String 0", ultraSound.getRange());
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() //This function is called periodically during test mode
	{
		LiveWindow.run();
	}
}
