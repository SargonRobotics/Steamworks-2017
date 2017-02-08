package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	private int _turnVal;
    public AutoTurn(int turnVal) 
    {
        requires(Robot.driveTrain);
        int _turnVal = turnVal;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	setTimeout(2); //TODO: set timeout time
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
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if (isTimedOut() == true)
    	{
    		Robot.driveTrain.turn(0);
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
