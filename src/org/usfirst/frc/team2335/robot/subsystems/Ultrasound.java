package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasound extends Subsystem
{
	Ultrasonic ultra;

	public Ultrasound()
	{
		ultra = new Ultrasonic(Robot.PULSE_PIN, Robot.ECHO_PIN);
		ultra.setAutomaticMode(true);
	}
	
	public double getRange()
	{
		return ultra.getRangeInches();
	}
	   
	public void initDefaultCommand()
	{
	        
	}
}

