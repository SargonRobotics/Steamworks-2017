package org.usfirst.frc.team2335.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public abstract class UltrasoundPID extends PIDSubsystem
{
	Ultrasonic backUltra;
	Ultrasonic frontUltra;
	
	private int amountOnTarget = 0;
	
	final double rangeCompensation = 26.5;
	
	public UltrasoundPID(double speed, double distance)
	{
		super("UltraPID", (speed / distance), 0.0, 0.0, 0.0, 0.02);
		setAbsoluteTolerance(1.0);
	}
	   
	public void initDefaultCommand()
	{
	        
	}
	
	public boolean isOnTarget()
	{
		amountOnTarget = onTarget() ? amountOnTarget + 1 : 0;
		
		if(amountOnTarget == 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}