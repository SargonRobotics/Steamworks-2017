package org.usfirst.frc.team2335.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class GyroPID extends PIDSubsystem
{
	ADXRS450_Gyro gyro;
	PIDController pid;
	 
	double storedAngle = 0;
	double pidOutput = 0;
	
	public int amountOnTarget = 0;
	
	static double speed = 0.05, distance = 2.5;
	 	
	private static final double kOscillationPeriod = 0.02;

    // Initialize your subsystem here
    public GyroPID()
    {    
    	super("GyroPID", (speed / distance), 0.0, 0.0, 0.0, kOscillationPeriod);
    	
    	setSetpoint(0.0);
    	setAbsoluteTolerance(0.5);
    	
    	gyro = new ADXRS450_Gyro();
    }

    public void init()
    {
    	gyro.calibrate();
    }
    
    public void reset()
    {
    	gyro.reset();
    }
    
    private double getFiltedValue()
    {
    	storedAngle = Math.abs(gyro.getRate()) < 0.7 ? storedAngle : gyro.getAngle();
    	
    	return storedAngle;
    }
    
    public double getAngle()
    {	
    	return getFiltedValue();
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	protected double returnPIDInput()
	{
		return getFiltedValue();
	}

	protected void usePIDOutput(double output)
	{
		pidOutput = -output;
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
