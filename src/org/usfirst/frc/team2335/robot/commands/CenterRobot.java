package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

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
    	Robot.vision.center();
    }

    protected boolean isFinished()
    {
        return Robot.vision.isCentered();
    }

    protected void end()
    {
    	
    }

    protected void interrupted()
    {
    	end();
    }
}
