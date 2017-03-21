package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasound extends Subsystem
{
	Ultrasonic backUltra;
	Ultrasonic frontUltra;
	
	//Make sure this value is accurate, so you don't make your team lose like Jack did
	double rangeCompensation = 26.5;

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
		//TODO: Fine tune values
		return getRangeBack() > (range - rangeCompensation) ? true : false;
	}
	
	public boolean atZeroInches()
	{
		return getRangeFront() < (rangeCompensation + 5) ? true : false;
	}
	   
	public void initDefaultCommand()
	{
	        
	}
}