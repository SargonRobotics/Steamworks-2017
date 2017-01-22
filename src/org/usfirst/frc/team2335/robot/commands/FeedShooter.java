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

    protected void execute() 
    {
        //Turns on feed motor
    	Robot.shooter.feedBall();
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end()
    {
        //Turns off feed motor
    	Robot.shooter.stopFeedBall();
    }


    protected void interrupted()
    {
    	end();	
    }
}
