package org.usfirst.frc.team2335.robot;

import org.usfirst.frc.team2335.robot.commands.CenterRobot;
import org.usfirst.frc.team2335.robot.commands.IntakeBalls;
import org.usfirst.frc.team2335.robot.commands.Lift;
import org.usfirst.frc.team2335.robot.commands.PositionRobot;
import org.usfirst.frc.team2335.robot.commands.StrafeLeft;
import org.usfirst.frc.team2335.robot.commands.StrafeRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperatorInterface
{	
	Joystick xbox;
	
	//Climb buttons
	JoystickButton climb;
	
	//Shoot buttons
	JoystickButton intakeBalls;
	
	//Vision buttons
	JoystickButton center, position, strafeLeft, strafeRight;
		
	/**
	 * OperatorInterface constructor. Always initialize last in robot init.
	 * Otherwise, it will crash since the OI file will try to access other subsystems once initialized.
	 * So if all the other subsystems aren't defined prior the code will crash.
	 */
	public OperatorInterface()
	{
		/**** Definitions ****/
		
		//Joysticks
		xbox = new Joystick(0);
		
		//Buttons
		strafeLeft = new JoystickButton(xbox, Robot.STRAFELEFT);
		strafeRight = new JoystickButton(xbox, Robot.STRAFERIGHT);
		
		//Climb buttons
		climb = new JoystickButton(xbox, Robot.CLIMB);
		
		//Shoot buttons
		intakeBalls = new JoystickButton(xbox, Robot.INTAKE_BUTTON);

		
		//Vision buttons
		center = new JoystickButton(xbox, Robot.CENTER);
		position = new JoystickButton(xbox, Robot.POSITION);
				
		
		//**** Links to Commands ****/
		
		//Drive commands
		strafeLeft.whileActive(new StrafeLeft());
		strafeRight.whileActive(new StrafeRight());
		
		//Climb commands
		climb.whileHeld(new Lift());
		
		//Shoot commands
		intakeBalls.toggleWhenPressed(new IntakeBalls());
		
		//Vision commands
		center.whenPressed(new CenterRobot());
		position.whenPressed(new PositionRobot());
		
	}
	
	public double getAxis(int axis, double max)
	{
		return deadzone(xbox.getRawAxis(axis), max);
	}
	
	//Returns button value so the command group can quit when it shuts off
	public boolean getShootButton()
	{
		return xbox.getRawButton(Robot.SHOOT_BUTTON);
	}
	
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
