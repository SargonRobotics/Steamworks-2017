package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class StopShooter extends Command {

    public StopShooter() 
    {
        requires(Robot.shooter);
    }

    //Length of command
    protected void initialize() 
    {
    	setTimeout(0.5);
    }

    //stops shoot motor
    protected void execute() 
    {
    	Robot.shooter.stopBall();
    }

    protected boolean isFinished() 
    {
        return isTimedOut();
    }

    protected void end() 
    {
    	
    }

    protected void interrupted() 
    {
    	end();
    }
}
