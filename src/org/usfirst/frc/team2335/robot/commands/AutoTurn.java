package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//TODO: Fine tune values

public class AutoTurn extends Command
{
	private double _turnVal;
	
    public AutoTurn(int turnVal) 
    {
        _turnVal = turnVal > 0 ? 0.3 : -0.3;
        
        requires(Robot.driveTrain);
        setTimeout(0.5); //TODO: Set correct timeout time. Value is amount of seconds until IsFinished returns true.
    }
    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Sets the motors to turn left or right based on the turnVal
    protected void execute()
    {
		 Robot.driveTrain.drive(0, _turnVal);
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
