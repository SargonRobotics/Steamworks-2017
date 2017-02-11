package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//TODO: Organize imports


public class AutoTurn extends Command
{

	private int _turnVal;
    public AutoTurn(int turnVal) 
    {
        requires(Robot.driveTrain);
        _turnVal = turnVal;
        setTimeout(0); //TODO: Set correct timeout time. Value is amount of seconds until IsFinished returns true.
    }
    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Sets the motors to turn left or right based on the turnVal
    protected void execute()
    {
		 if(_turnVal > 0)
		 {
			 Robot.driveTrain.drive(1, 1); //TODO: Find a turn rate and replace the second value with it 
		 }
		 else
		 {
			 Robot.driveTrain.drive(1, -1); //TODO: replace -1 with the inverse of the previous value
    	 }
    }

    // Ends the command when the timer runs out
    protected boolean isFinished() 
    {
    	
    	return isTimedOut();
    	
    }

    // Stops the motors at the end of the command
    protected void end() 
    {
    	Robot.driveTrain.stopDrive();
    }

    // Ends the commannd
    protected void interrupted() 
    { 
    	end();
    }
}
