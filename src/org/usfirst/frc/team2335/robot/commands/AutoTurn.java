package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//TODO: Organize imports

//TODO: FORMATTING FOR GODS SAKE
public class AutoTurn extends Command {

	private int _turnVal;
	//TODO: MAKE IT READABLE, EMPTY LINE HERE
    public AutoTurn(int turnVal) 
    {
        requires(Robot.driveTrain);
        int _turnVal = turnVal;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	//How come setTimeout is used in the constructor in other commands, and in init here?
    	//Keep it consistent, move it to the contructor
    	
    	//TODO: FOR THE LOVE OF DEAD HIMSELF MAKE YOUR COMMENTS MEAN SOMETHING
    	setTimeout(2); //TODO: set timeout time
    	//WHAT DOES TIMEOUT TIME MEAN? WHAT DO YOU NEED TO SET IT TO?
    	//WHY NOT DO IT NOW?
    	
    	//This code triggers me... don't to it again
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
		 if(_turnVal > 0)
		 {
			 Robot.driveTrain.turn(1); //TODO: get a good turn rate
		 }
		 else
		 {
			 Robot.driveTrain.turn(-1);
    	 }
    	//TODO:Formatting
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	//This is all wrong, if you want the explination look in AutoStrafe
/*    	if (isTimedOut() == true)
    	{
    		Robot.driveTrain.turn(0);
    	}
        return false; */
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	//TODO: Add a function to stop our drive motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { //TODO: Formatting
    	//TODO: Run end() here
    }
}
