package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.CenterRobot;
import org.usfirst.frc.team2335.robot.commands.IntakeBalls;
import org.usfirst.frc.team2335.robot.commands.PositionRobot;
import org.usfirst.frc.team2335.robot.commands.StartShooter;
import org.usfirst.frc.team2335.robot.commands.Strafe;
import org.usfirst.frc.team2335.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//TODO: Rename to OperatorInterface after basic functionality for clarity.
//This class connects operator control to different commands
public class OI
{	
	Joystick controller, xbox;
	JoystickButton center, position, shootBall, intakeBalls;
	Axis strafe;
		
	/**
	 * OperatorInterface constructor. Always initialize last in robot init.
	 * Otherwise, it will crash since TODO: FILL-IN REASON.
	 */
	public OI()
	{
		/**** Definitions ****/
		
		//Joystick Definition
		controller = new Joystick(0);
		xbox = new Joystick(1);
		
		///Button Definition
		shootBall = new JoystickButton(controller, Robot.SHOOT_BUTTON);
		center = new JoystickButton(controller, Robot.CENTER);
		position = new JoystickButton(controller, Robot.POSITION);
		intakeBalls = new JoystickButton(xbox, Robot.INTAKE_BUTTON);
		
		//Axis Definition
		strafe = new Axis(controller, Robot.STRAFE);
	
		/**** Commands ****/
		
		//Drive commands
		strafe.whileActive(new Strafe());
		
		//Shooter commands
		shootBall.whileHeld(new StartShooter()); //Change to ShootGroup when build adds index
		
		//Intake commands
		intakeBalls.toggleWhenPressed(new IntakeBalls());
		
		//Vision commands
		center.whenPressed(new CenterRobot());
		position.whenPressed(new PositionRobot());
	}
	
	public double getAxis(int axis, double max)
	{		
		return deadzone(controller.getRawAxis(axis), max);
	}
	
	//TODO: Add javadoc-style comments to every function after a javadoc
	//		standard is decided.
	private static double deadzone(double amount, double max) //Creates a deadzone for the axes of the controller
	{
    	//If the value from the controller is less than the deadzone value then it zeros out
    	//If not it subtracts the deadzone from the controller value
		amount = -(Math.abs(amount) <= Robot.DEADZONE ? 0 : (amount = (amount < 0) ? amount : amount));
		
		//Multiplies the controller value by the slope made from (y2 - y1) / (x2 - x1)
		return ((max - 0) / ((1 - Robot.DEADZONE) - 0) * (amount - 0));
	}
}