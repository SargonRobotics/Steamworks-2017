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
		requires(Robot.backUltraPID);
		requires(Robot.driveTrain);
		   
		_distance = distance; 
    }

    // Called just before this Command runs the first time, We don't need to do anything
    protected void initialize() 
    {
    	Robot.backUltraPID.setSetpoint(_distance);
    	
    	Robot.turnCorrectionPID.enable();
    	Robot.backUltraPID.enable();
    }

    // Sets the motors to drive
    protected void execute() 
    {
    	Robot.driveTrain.driveAwayAuto();
    }

    // Ends the command once the ultrasonic is far enough from the wall
    protected boolean isFinished()
    {
    	return Robot.backUltraPID.isOnTarget();
    }

    // Stops the motors at the end of the command
    protected void end() 
    { 
    	Robot.driveTrain.stopDrive();
    	Robot.gyroPID.disable();
    	Robot.backUltraPID.disable();
    	
    	DriverStation.reportError("DONE", true);
    }

    // Ends command
    protected void interrupted() 
    {
    	end();
    }
}
