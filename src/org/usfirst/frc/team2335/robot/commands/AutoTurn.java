package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//TODO: Fine tune values

public class AutoTurn extends Command
{
	private double _turnVal;
	
    public AutoTurn(int turnVal) 
    {
        _turnVal = turnVal > 0 ? 45 : -45;
        
        requires(Robot.driveTrain);
        requires(Robot.gyroPID);
    }
    // Called just before this Command runs the first time
    protected void initialize() 
    {    	
    	Robot.gyroPID.enable();    	
    	Robot.gyroPID.setSetpoint(_turnVal);
    }

    // Sets the motors to turn left or right based on the turnVal
    protected void execute()
    {
		Robot.driveTrain.turnAuto();
    }

    // Ends the command when the timer runs out
    protected boolean isFinished() 
    {
    	return Robot.gyroPID.isOnTarget();
    }

    // Stops the motors at the end of the command
    protected void end() 
    {
    	Robot.driveTrain.stopDrive();
    	Robot.gyroPID.disable();
    }

    // Ends the commannd
    protected void interrupted() 
    { 
    	end();
    }
}
