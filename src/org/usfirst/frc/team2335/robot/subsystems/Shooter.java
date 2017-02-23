package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Shooter extends Subsystem 
{
	//Motors for the shooter
	private Victor feederMotor;
	private TalonSRX shooterMotor;
	private TalonSRX intakeMotor;
	
	public double motorSpeed = -0.68;
	
	public Shooter()
	{
		super();
		
		//Defines motors for the shooter
		feederMotor = new Victor(Robot.FEEDER_MOTOR);
		shooterMotor = new TalonSRX(Robot.SHOOTER_MOTOR);	
		intakeMotor = new TalonSRX(Robot.INTAKE_MOTOR);
	}
		
	//Shooter motor methods
	public void shootBall()
	{
		//TODO: Find best speed for shooter motor
		shooterMotor.set(motorSpeed);
	}
	
	public void speedUp()
	{
		motorSpeed -= 0.05;
	}
	
	public void speedDown()
	{
		motorSpeed += 0.05;
	}
	
	public void stopBall()
	{
		shooterMotor.set(0);
	}
	
	public void startIntake()
	{
		intakeMotor.set(1);
	}
	
	public void stopIntake()
	{
		intakeMotor.set(0);
	}
	
	//Feeder motor methods
	public void feedBall()
	{
		//TODO: find best speed for feed motor
		feederMotor.set(-1);
	}
	
	public void stopFeedBall()
	{
		feederMotor.set(0);
	}
	
	public boolean isShootButtonReleased()
	{
		return Robot.oi.getShootButton();
	}
    
    public void initDefaultCommand()
    {
       
    }
}

