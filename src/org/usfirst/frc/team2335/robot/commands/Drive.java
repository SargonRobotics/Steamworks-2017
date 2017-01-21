package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command 
{
    public Drive() 
    {
       requires(Robot.driveTrain);
    }
    
    protected void initialize() 
    {
    
    }
    
    protected void execute()
    {
    	Robot.driveTrain.drive(Robot.oi.getAxis(Robot.MOVE, 0.95));
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
