package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Strafe extends Command 
{
    public Strafe() 
    {
       requires(Robot.driveTrain);
    }

    protected void initialize()
    {
        
    }
    
    protected void execute() 
    {
    	Robot.driveTrain.strafe(Robot.oi.getAxis(Robot.STRAFE, 1));
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
