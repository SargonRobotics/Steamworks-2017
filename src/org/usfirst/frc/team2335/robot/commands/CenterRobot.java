package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class CenterRobot extends Command
{
    public CenterRobot()
    {
        requires(Robot.vision);
    }

    protected void initialize()
    {
    	
    }

    protected void execute()
    {
    	if(Robot.vision.center() == 1)
    	{
    		Robot.driveTrain.strafe(-0.2);
    	}
    	else if(Robot.vision.center() == -1)
    	{
    		Robot.driveTrain.strafe(0.2);
    	}
    }

    protected boolean isFinished()
    {
        return Robot.vision.isCentered();
    }

    protected void end()
    {
    	DriverStation.reportWarning("Centered", true);
    	Robot.driveTrain.stopStrafe();
    }

    protected void interrupted()
    {
    	end();
    }
}
