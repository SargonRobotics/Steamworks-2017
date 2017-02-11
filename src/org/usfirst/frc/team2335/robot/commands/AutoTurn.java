package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//TODO: Organize imports

//TODO: FORMATTING FOR GODS SAKE
public class AutoTurn extends Command {

	private int _turnVal;
    public AutoTurn(int turnVal) 
    {
        requires(Robot.driveTrain);
        int _turnVal = turnVal;
        setTimeout(0); //TODO: Set correct timeout time. Value is amount of seconds until IsFinished returns true.
    }
    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
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
    	
    	if (isTimedOut() == true)
    	{
    		return true;
    	}
    	else
    	{
    		 return false;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.driveTrain.turn(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    { 
    	Robot.driveTrain.turn(0);
    }
}
