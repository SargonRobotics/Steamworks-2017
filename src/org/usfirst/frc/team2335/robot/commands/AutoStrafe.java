package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * 
 */
public class AutoStrafe extends Command {
	//Seriously, formatting here at the top
	
    public AutoStrafe() { //TODO: Formatting
        requires(Robot.ultraSound);
        requires(Robot.driveTrain);
        setTimeout(3);
    }

    // Called just before this Command runs the first time
    protected void initialize() { //TODO: Formatting
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//TODO: make more descriptive comments
    	Robot.driveTrain.strafe(0.5); //TODO: make good strafe value
    }

    //Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	//I'd like to know what you were thinking, because this makes no sense
    	//You'd set your strafe motor to 0 in end, not here
    	//It's a boolean method, so it returns true or false, by saying it always returns false the robot would've
    	//never stopped, you even used isTimedOut to see if the time is over
    	//So you return that value to see if, in our case 3 seconds has passed, and in end() you stop the motors
    	
/*    	if(isTimedOut() == true)
    	{
    		Robot.driveTrain.strafe(0);
    	}
        return false; */
    	
    	
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	//TODO: Add stop motor function
    	//This is where you would stop the motor, not in the boolean isFinished method
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	//Remember to always run end from interrupted for safety
    }
}
