package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class ClimbCommand extends Command {

    public ClimbCommand() 
    {
        requires(Robot.climb);
    }

    
    protected void initialize() 
    {
    	
    }

   
    protected void execute() 
    {
    	Robot.climb.startClimb();
    	//Turns on motor
    }

    
    protected boolean isFinished() 
    {
        return false;
    }

    
    protected void end() 
    {
    	Robot.climb.stopClimb();
    	//Turns off motor
    }

    protected void interrupted() 
    {
    	end();
    }
}
