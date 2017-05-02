package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class StartShooter extends Command {

    public StartShooter() 
    {
       requires(Robot.shooter);
    }

    protected void initialize()
    {
    	//TODO: find real time needed for motor to rev up
    	setTimeout(1);
    }

    protected void execute() 
    {
        // Turns on shoot motor
    	Robot.shooter.shootBall();
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
