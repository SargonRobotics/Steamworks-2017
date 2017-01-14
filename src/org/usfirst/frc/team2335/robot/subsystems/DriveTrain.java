package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem
{
	RobotDrive drive;
	Talon strafe;
	
	public DriveTrain()
	{
		drive = new RobotDrive(Robot.LEFTDRIVE, Robot.RIGHTDRIVE);
		strafe = new Talon(Robot.STRAFE);
    }
	
	public void drive(double moveval)
	{ 
		drive.arcadeDrive(moveval, 0);
	}
	
	public void turn(double rotateval)
	{ 
		drive.arcadeDrive(0, rotateval);
	}
	
	public void stopDrive()
	{
			drive.arcadeDrive(0,0);
	}
	
	public void strafe(double strafeval)
	{ 
		strafe.set(strafeval);
	}
	
	public void stopStrafe()
	{
		strafe.set(0);
	}
	
	
	@Override
	protected void initDefaultCommand()
	{
		
	}
}

