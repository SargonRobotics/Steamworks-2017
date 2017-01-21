package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem 
{
	//Creates Motors for the Shooter
	Victor indexMotor;
	Victor shooterMotor;
	
	public Shooter()
	{
		super();
		
		//Defines Motors for the Shooter
		indexMotor = new Victor(Robot.FEEDER_WHEEL);
		shooterMotor = new Victor(Robot.SHOOTER_WHEEL);	
	}
		
	//Creates methods for Shooter command group
	public void shootBall()
	{
		shooterMotor.set(0.6);
	}
	
	public void feedBall()
	{
		//TODO: find best speed for feed motor
		indexMotor.set(0.5);
	}
	
	public void stopFeedBall()
	{
		indexMotor.set(0);
	}
	
	public void stopBall()
	{
		shooterMotor.set(0);
	}
    
    public void initDefaultCommand()
    {
       
    }
}

