package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//There was an empty comment here I needed to remove, and your curly bracket placement is very inconsistent

public class AutoDrive extends Command {
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
    protected void execute() { //TODO: Formatting
    	//Wtf even is that comment, what IS a good drive value?
    	//Make these comments make sense
    	Robot.driveTrain.drive(0.5); //TODO: make good drive value
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
    	//This is all wrong, if you want the explination look in AutoStrafe
/*    	if(Double.valueOf(Robot.ultraSound.getRange()) < _distance);
    	
    	{
    		Robot.driveTrain.drive(0);
    	}
    	
        return false; */
    }

    // Called once after isFished returns true
    protected void end() { //TODO: Formatting
    	//TODO: Add a function to stop our drive motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { //TODO: Formatting
    	//TODO: Call end here
    }
}
