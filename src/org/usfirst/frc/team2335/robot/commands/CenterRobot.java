package org.usfirst.frc.team2335.robot.commands;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putString("DB/String 0", Integer.toString(Robot.vision.center()));
    	
    	if(Robot.vision.center() == 1)
    	{
    		//Robot.driveTrain.strafe(-0.5);
    	}
    	else if(Robot.vision.center() == -1)
    	{
    		//Robot.driveTrain.strafe(-0.5);
    	}
    	
    	System.out.print(Robot.width + ", ");
    	System.out.print(Robot.height + ", ");
    	System.out.print(Robot.x + ", ");
    	System.out.println(Robot.y); 
    }

    protected boolean isFinished()
    {
        return Robot.vision.isCentered();
    }

    protected void end()
    {
    	Robot.driveTrain.stopStrafe();
    }

    protected void interrupted()
    {
    	end();
    }
}
