package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoStrafe extends Command 
{
    public AutoStrafe() 
    { 
        requires(Robot.ultraSound);
        requires(Robot.driveTrain);
        setTimeout(3); //Creates time limit for command in seconds //TODO: test for the correct amount of time
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	
    }

    //Starts the strafe motor
    protected void execute() 
    {
    	//TODO: Test for correct strafe motor speed
    	Robot.driveTrain.strafe(0.5); 
    }

    //Stops command after the timer is done
    protected boolean isFinished() 
    {	
    	return isTimedOut();
    }

    // Stops drive motors at end of command
    protected void end() 
    {
    	Robot.driveTrain.stopDrive();
    }

    //Ends the program
    protected void interrupted() 
    {
    	end();
    }
}
