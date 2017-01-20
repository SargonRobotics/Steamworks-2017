package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command 
{
    public Turn()
    {
        requires(Robot.driveTrain);
    }

    protected void initialize() 
    {
    
    }

    protected void execute() 
    {
    	Robot.driveTrain.turn(Robot.oi.getAxis(Robot.ROTATE, 0.65));
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

    }
}
