package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.Drive;
import org.usfirst.frc.team2335.robot.commands.ShootGroup;
import org.usfirst.frc.team2335.robot.commands.Strafe;
import org.usfirst.frc.team2335.robot.commands.Turn;
import org.usfirst.frc.team2335.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI
{	
	Joystick controller = new Joystick(0);
	
	public OI()
	{
		//Buttons
		Button shootBall = new JoystickButton(controller, Robot.SHOOT);
		
		//Axes
		Axis strafe = new Axis(controller, Robot.STRAFE);
		Axis move = new Axis(controller, Robot.MOVE);
		Axis rotate = new Axis(controller, Robot.ROTATE);
	
		//Drive commands
		strafe.whileActive(new Strafe());
		move.whileActive(new Drive());
		rotate.whileActive(new Turn());
		
		//Shooter commands
		shootBall.whileHeld(new ShootGroup());
	}
	
	public double getAxis(int axis, double max)
	{
		return deadzone(controller.getRawAxis(axis), max);
	}
	
	public static double deadzone(double amount, double max) //Creates a deadzone for the axes of the controller
	{
    	//If the value from the controller is less than the deadzone value then it zeros out
    	//If not it subtracts the deadzone from the controller value
		amount = (Math.abs(amount) <= Robot.DEADZONE ? 0 : (amount = (amount < 0) ? amount : amount));
		
		//Multiplies the controller value by the slope made from (y2 - y1) / (x2 - x1)
		return ((max - 0) / ((1 - Robot.DEADZONE) - 0) * (amount - 0));
	}
}
