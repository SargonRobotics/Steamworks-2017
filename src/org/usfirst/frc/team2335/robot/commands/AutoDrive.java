package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command
{
	private double _distance;
	
    public AutoDrive(double distance)
    {
       requires(Robot.ultraSound);
       requires(Robot.driveTrain);
       
       _distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(0.5); //TODO: make good drive value
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
    	if(Double.valueOf(Robot.ultraSound.getRange()) < _distance);
    	
    	{
    		Robot.driveTrain.drive(0);
    	}
    	
        return false;
    }

    // Called once after isFished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
