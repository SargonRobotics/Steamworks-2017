package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot
{	
	//Constants:
	public static final double DEADZONE = 0.1;
	
	//Axes:
	public static final int MOVE = 0, ROTATE = 0, SIDESTEP = 0;
	//TODO: Define constants for controllers
	
	//Motor ports:
	public static final int LEFTDRIVE = 0, RIGHTDRIVE = 1, STRAFE = 2;
	
	
	//Subsystems:
	public static DriveTrain driveTrain;
	public static OI oi;

	//Auto:
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser();

	
	@Override
	public void robotInit() //Runs once to initialize all global variables
	{
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
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() //This function is called periodically during test mode
	{
		LiveWindow.run();
	}
	
	 public static double deadzone(double amount, double max) //Creates a deadzone for the axes of the controller
		{
	    	//If the value from the controller is less than the deadzone value then it zeros out
	    	//If not it subtracts the deadzone from the controller value
			amount = -(Math.abs(amount) <= Robot.DEADZONE ? 0 : (amount = (amount < 0) ? amount : amount));
			
			//Multiplies the controller value by the slope made from (y2 - y1) / (x2 - x1)
			return ((max - 0) / ((1 - Robot.DEADZONE) - 0) * (amount - 0));
		}
}
