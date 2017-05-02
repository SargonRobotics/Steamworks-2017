package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

public class BackUltraPID extends UltrasoundPID
{
	public double pidOutput;
	
	
    // Initialize your subsystem here
    public BackUltraPID()
    {
        super(0.2, 5);
    }

    public void initDefaultCommand()
    {
 
    }
    
    protected double returnPIDInput()
    {
        return Robot.ultrasound.getBackRange();
    }

    protected void usePIDOutput(double output)
    {
        pidOutput = output;
    }
}
