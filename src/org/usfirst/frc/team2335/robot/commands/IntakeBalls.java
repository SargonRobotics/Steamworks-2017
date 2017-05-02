package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBalls extends Command
{
    public IntakeBalls()
    {
        requires(Robot.shooter);
    }

    protected void initialize() 
    {
    	
    }

    protected void execute()
    {
    	Robot.shooter.startIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.shooter.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
