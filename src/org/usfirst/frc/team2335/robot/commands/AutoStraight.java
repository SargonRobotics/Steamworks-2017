package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoStraight extends Command
{
    public AutoStraight()
    {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	Robot.driveTrain.drive(1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return isTimedOut();
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
