package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem 
{
	//Motors for the shooter
	Victor indexMotor;
	Victor shooterMotor;
	
	public Shooter()
	{
		super();
		
		//Defines motors for the shooter
		indexMotor = new Victor(Robot.FEEDER_MOTOR);
		shooterMotor = new Victor(Robot.SHOOTER_MOTOR);	
	}
		
	//Shooter motor methods
	public void shootBall()
	{
		//TODO: Find best speed for shooter motor
		shooterMotor.set(0.6);
	}
	
	public void stopBall()
	{
		shooterMotor.set(0);
	}
	
	//Feeder motor methods
	public void feedBall()
	{
		//TODO: find best speed for feed motor
		indexMotor.set(0.5);
	}
	
	public void stopFeedBall()
	{
		indexMotor.set(0);
	}
    
    public void initDefaultCommand()
    {
       
    }
}

