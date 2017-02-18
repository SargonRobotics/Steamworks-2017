package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasound extends Subsystem
{
	Ultrasonic backUltra;
	Ultrasonic frontUltra;

	public Ultrasound()
	{
		backUltra = new Ultrasonic(Robot.BACK_PING, Robot.BACK_ECHO);		
		frontUltra = new Ultrasonic(Robot.FRONT_PING, Robot.FRONT_ECHO);
		
		backUltra.setAutomaticMode(true);
		frontUltra.setAutomaticMode(true);
	}
	
	public double getRangeBack()
	{
		return backUltra.getRangeInches();
	}
	
	public double getRangeFront()
	{
		return frontUltra.getRangeInches();
	}
	
	public boolean atRange(double range)
	{	
		return range > getRangeBack() ? true : false;
	}
	
	public boolean atZeroInches()
	{
		return getRangeFront() < 5 ? true : false;
	}
	   
	public void initDefaultCommand()
	{
	        
	}
}