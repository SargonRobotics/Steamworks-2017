package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveAway extends Command 
{
	private double _distance; //Creates distance passing parameter which is the distance that the ultrasonic needs to be from the wall
	
	//Defines are 
    public AutoDriveAway(double distance)
    {
       requires(Robot.ultraSound);
       requires(Robot.driveTrain);
       
       _distance = distance; 
    }

    // Called just before this Command runs the first time, We don't need to do anything
    protected void initialize() 
    {
    	
    }

    // Sets the motors to drive
    protected void execute() 
    {
    	Robot.driveTrain.drive(0.6, 0); //TODO: Find the correct drive speed to put in place of 0.5
    }

    // Ends the command once the ultrasonic is far enough from the wall
    protected boolean isFinished()
    {
    	return Robot.ultraSound.atRange(_distance);
    }

    // Stops the motors at the end of the command
    protected void end() 
    { 
    	DriverStation.reportWarning("Finished", true);
    	Robot.driveTrain.stopDrive();
    }

    // Ends command
    protected void interrupted() 
    {
    	end();
    }
}
