package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class FeedShooter extends Command {

    public FeedShooter() 
    {
        requires(Robot.shooter);
    }

    protected void initialize() 
    {
    	
    }

    //Turns on feed motor
    protected void execute() 
    {
    	Robot.shooter.feedBall();
    }

    protected boolean isFinished() 
    {
        return false;
    }

    //Turns off feed motor
    protected void end()
    {
    	Robot.shooter.stopFeedBall();
    }


    protected void interrupted()
    {
    	end();	
    }
}
