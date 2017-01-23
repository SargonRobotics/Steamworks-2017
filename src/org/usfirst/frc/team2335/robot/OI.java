package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.CenterRobot;
import org.usfirst.frc.team2335.robot.commands.Drive;
import org.usfirst.frc.team2335.robot.commands.Strafe;
import org.usfirst.frc.team2335.robot.commands.Turn;
import org.usfirst.frc.team2335.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//TODO: Rename to OperatorInterface after basic functionality for clarity.
//This class connects operator control to different commands
public class OI
{	
	Joystick controller = new Joystick(0);
	
	/**
	 * OperatorInterface constructor. Always initialize last in robot init.
	 * Otherwise, it will crash since TODO: FILL-IN REASON.
	 */
	public OI()
	{
		//Axes
		Axis strafe = new Axis(controller, Robot.STRAFE);
		Axis move = new Axis(controller, Robot.MOVE);
		Axis rotate = new Axis(controller, Robot.ROTATE);
		
		///Buttons
		JoystickButton center = new JoystickButton(controller, Robot.CENTER);
		
		//Drive commands
		strafe.whileActive(new Strafe());
		move.whileActive(new Drive());
		rotate.whileActive(new Turn());
		
		//Vision commands
		center.whenPressed(new CenterRobot());
	}
	
	public double getAxis(int axis, double max)
	{
		return deadzone(controller.getRawAxis(axis), max);
	}
	
	//TODO: Add javadoc-style comments to every function after a javadoc
	//		standard is decided.
	public static double deadzone(double amount, double max) //Creates a deadzone for the axes of the controller
	{
    	//If the value from the controller is less than the deadzone value then it zeros out
    	//If not it subtracts the deadzone from the controller value
		amount = -(Math.abs(amount) <= Robot.DEADZONE ? 0 : (amount = (amount < 0) ? amount : amount));
		
		//Multiplies the controller value by the slope made from (y2 - y1) / (x2 - x1)
		return ((max - 0) / ((1 - Robot.DEADZONE) - 0) * (amount - 0));
	}
}