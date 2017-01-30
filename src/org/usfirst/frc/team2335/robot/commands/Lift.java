package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command
{
    public Lift() 
    {
        requires(Robot.climb);
    }

    
    protected void initialize() 
    {
    	
    }

    protected void execute() 
    {
    	//Starts the climb process
    	Robot.climb.startClimb();
    }
    
    protected boolean isFinished() 
    {
        return false;
    }
    
    protected void end() 
    {
    	//Stops the climb motor
    	Robot.climb.stopClimb();
    }

    protected void interrupted() 
    {
    	end();
    }
}
