package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Climb extends Subsystem 
{
    // Motor for climbing
	Victor climbMotor;
	
	public Climb()
	{
		super();
		
		//Defines motor for climbing mechanism
		climbMotor = new Victor(Robot.CLIMB_PORT);
		
	}
	
	public void startClimb()
	{
		climbMotor.set(0.5);
		//TODO Find real value
	}
	
	public void stopClimb()
	{
		climbMotor.set(0);
	}
   
	public void initDefaultCommand() 
    {
    	
    }
}

