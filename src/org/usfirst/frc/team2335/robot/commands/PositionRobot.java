package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PositionRobot extends Command
{
	//TODO: Test out this command to see if it works properly
    public PositionRobot()
    {
        requires(Robot.vision);
    }

    protected void initialize()
    {
    	
    }

    protected void execute()
    {
    	if(Robot.vision.position() == 1)
    	{
    		Robot.driveTrain.drive(0.2);
    	}
    	else if(Robot.vision.position() == -1)
    	{
    		Robot.driveTrain.drive(-0.2);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return Robot.vision.isPositioned();
    }

    protected void end() 
    {
    	Robot.driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
