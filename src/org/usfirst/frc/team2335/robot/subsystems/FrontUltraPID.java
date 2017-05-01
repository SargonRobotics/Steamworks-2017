package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

public class FrontUltraPID extends UltrasoundPID
{
	public double pidOutput;
		
    // Initialize your subsystem here
    public FrontUltraPID()
    {
        super(0.6, 45);
    }
    
    public void initDefaultCommand()
    {

    }

	@Override
	protected double returnPIDInput()
	{
		return Robot.ultrasound.getFrontRange();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		pidOutput = -output;
	}
    
    
}
