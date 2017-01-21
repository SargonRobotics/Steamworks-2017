package org.usfirst.frc.team2335.robot.commands;

import java.util.Set;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class StartShooter extends Command {

    public StartShooter() 
    {
       requires(Robot.shooter);
    }

    //Length of Command
    protected void initialize()
    {
    	//TODO: find real time needed for motor to rev up
    	setTimeout(0.5);
    }

    // Turns on Shoot motor
    protected void execute() 
    {
    	Robot.shooter.shootBall();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() 
    {
    	end();
    }


    protected void interrupted() {
    }
}
