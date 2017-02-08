package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasound extends Subsystem
{
	Ultrasonic ultra;
	DigitalOutput reset;
	double lastDistance = 0, tmp = 0;

	public Ultrasound()
	{
		reset = new DigitalOutput(Robot.RESET_PIN);
		ultra = new Ultrasonic(Robot.PULSE_PIN, Robot.ECHO_PIN);
		ultra.setAutomaticMode(true);
	}
	
	public String getRange()
	{
		double valueInInches;
		
		valueInInches = ultra.getRangeInches();
		
		System.out.println(tmp);
		
		if(valueInInches != lastDistance)
		{
			tmp = 0;
			lastDistance = valueInInches;
			return Double.toString(valueInInches);
		}
		else if(tmp == 15)
		{
			tmp = 0;
			resetSensor();
			return "Sensor reset";
		}
		else
		{
			lastDistance = valueInInches;
			tmp++;
			return Double.toString(valueInInches);
		}
	}
	
	private void resetSensor()
	{
		reset.set(true);
		reset.set(false);
	}
	   
	public void initDefaultCommand()
	{
	        
	}
}

