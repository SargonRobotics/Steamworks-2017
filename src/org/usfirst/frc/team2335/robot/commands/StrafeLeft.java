package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeLeft extends Command 
{	
    public StrafeLeft() 
    {
       requires(Robot.driveTrain);
    }

    protected void initialize()
    {
        
    }
    
    protected void execute() 
    {
    	Robot.driveTrain.strafe(-1);
    }
    
    protected boolean isFinished() 
    {
        return false;
    }
    
    protected void end() 
    {
    	Robot.driveTrain.stopStrafe();
    }
    
    protected void interrupted() 
    {
        end();
    }
}
