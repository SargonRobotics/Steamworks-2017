package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FindTape extends Command
{
    public FindTape()
    {
        requires(Robot.vision);
    }

    protected void initialize()
    {
    	SmartDashboard.putString("DB/String 0", "Area:");
    	SmartDashboard.putString("DB/String 2", "CenterX:");
    	SmartDashboard.putString("DB/String 5", "CenterY:");
    }

    protected void execute()
    {
    	SmartDashboard.putString("DB/String 1", Double.toString(Robot.vision.getArea()));
    	SmartDashboard.putString("DB/String 3", Double.toString(Robot.vision.getCenterX()));
    	SmartDashboard.putString("DB/String 6", Double.toString(Robot.vision.getCenterY()));
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
    	
    }

    protected void interrupted()
    {
    	end();
    }
}
