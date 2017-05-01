package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveTo extends Command
{
    public AutoDriveTo()
    {
        requires(Robot.driveTrain);
        requires(Robot.frontUltraPID);
        requires(Robot.gyroPID);
    }

    protected void initialize()
    {
    	Robot.turnCorrectionPID.enable();
    	Robot.frontUltraPID.enable();
    	
    	Robot.frontUltraPID.setSetpoint(0.0);
    }

    protected void execute()
    {
    	Robot.driveTrain.driveToAuto();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return Robot.frontUltraPID.isOnTarget();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.driveTrain.stopDrive();
    	Robot.gyroPID.disable();
    	Robot.frontUltraPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
