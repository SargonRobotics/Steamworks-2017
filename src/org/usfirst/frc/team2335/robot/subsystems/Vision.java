package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem
{
    public double getArea()
    {
    	return Robot.area;
    }
    
    public double getCenterX()
    {
    	return Robot.centerX;
    }
    
    public double getCenterY()
    {
    	return Robot.centerY;
    }
	
    public void initDefaultCommand()
    {
        
    }
}

