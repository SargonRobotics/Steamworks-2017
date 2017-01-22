package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class StopShooter extends Command {

    public StopShooter() 
    {
        requires(Robot.shooter);
    }

    protected void initialize() 
    {
    	setTimeout(0.5);
    }

    protected void execute() 
    {
        //Stops shoot motor
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
