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
    	SmartDashboard.putString("DB/String 0", "Average:");
    }

    protected void execute()
    {
    	SmartDashboard.putString("DB/String 1", Double.toString(Robot.vision.getArea()));
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
