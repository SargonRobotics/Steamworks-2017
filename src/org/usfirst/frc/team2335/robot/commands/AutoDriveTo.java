package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveTo extends Command
{
    public AutoDriveTo()
    {
        requires(Robot.driveTrain);
        requires(Robot.ultraSound);
    }

    protected void initialize()
    {
    	
    }

    protected void execute()
    {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return Robot.ultraSound.atZeroInches();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
