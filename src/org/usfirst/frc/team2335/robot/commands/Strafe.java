package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Strafe extends Command 
{
	private int _joy;
	
    public Strafe(int joy) 
    {
       requires(Robot.driveTrain);
       _joy = joy;
    }

    protected void initialize()
    {
        
    }
    
    protected void execute() 
    {
    	if(Robot.oi.getAxis(_joy, Robot.MOVE, 1) == 0 && Robot.oi.getAxis(_joy, Robot.ROTATE, 1) == 0)
    	{
    		Robot.driveTrain.strafe(Robot.oi.getAxis(_joy, Robot.STRAFE, 1));
    	}
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
