package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.CenterRobot;
import org.usfirst.frc.team2335.robot.commands.PositionRobot;
import org.usfirst.frc.team2335.robot.commands.Strafe;
import org.usfirst.frc.team2335.robot.triggers.Axis;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperatorInterface
{	
	Joystick controller, xbox;
	JoystickButton center, position;
	Axis strafe;
	
	/**
	 * OperatorInterface constructor. Always initialize last in robot init.
	 * Otherwise, it will crash since the OI file will try to access other subsystems once initialized.
	 * So if all the other subsystems aren't defined prior the code will crash.
	 */
	public OperatorInterface()
	{
		//Joysticks
		controller = new Joystick(0);
		xbox = new Joystick(1);
		
		//Axes
		strafe = new Axis(controller, Robot.STRAFE);
		
		///Buttons
		center = new JoystickButton(controller, Robot.CENTER);
		position = new JoystickButton(controller, Robot.POSITION);
				
		//Drive commands
		strafe.whileActive(new Strafe());
		
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
	
	public void printPOV()
	{
		SmartDashboard.putString("DB/String 5", Integer.toString(xbox.getPOV()));
	}
}