package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem
{
	RobotDrive drive;
	TalonSRX strafe;
	
	public DriveTrain()
	{
		drive = new RobotDrive(Robot.LEFT_PORT, Robot.RIGHT_PORT);
		strafe = new TalonSRX(Robot.STRAFE_PORT);
    }
	
	public void drive(double moveVal)
	{ 
		drive.arcadeDrive(moveVal, 0);
	}
	
	public void turn(double rotateVal)
	{ 
		drive.arcadeDrive(0, rotateVal);
	} 
	
	public void stopDrive()
	{
		drive.arcadeDrive(0,0);
	}
	
	public void strafe(double strafeVal)
	{ 
		strafe.set(strafeVal);
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

