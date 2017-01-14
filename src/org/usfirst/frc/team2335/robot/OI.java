package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.Drive;
import org.usfirst.frc.team2335.robot.commands.Strafe;
import org.usfirst.frc.team2335.robot.commands.Turn;
import org.usfirst.frc.team2335.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;

//This class connects operator control to different commands
public class OI
{	
	Joystick controller = new Joystick(0);
	
	public OI()
	{
		//Axes
		Axis move = new Axis(controller, Robot.MOVE);
		Axis rotate = new Axis(controller, Robot.ROTATE);
		Axis strafe = new Axis(controller, Robot.STRAFE);
		
		//Drive Commands
		move.whileActive(new Drive());
		rotate.whileActive(new Turn());
		strafe.whileActive(new Strafe());
	}
	
	public double getAxis(int axis, double max)
	{
		return Robot.deadzone(controller.getRawAxis(axis), max);
	}
}
