package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem
{
	Victor leftDrive, rightDrive;
	TalonSRX strafe;
	
	public DriveTrain()
	{		
		leftDrive = new Victor(Robot.LEFT_PORT);
		leftDrive.setInverted(false);
		
		rightDrive = new Victor(Robot.RIGHT_PORT);
		rightDrive.setInverted(true);
		
		strafe = new TalonSRX(Robot.STRAFE_PORT);
    }
	
	public void drive(double moveVal, double rotateVal)
	{	
		//Inverts moveVal for correct driving use
		
		double leftMotorVal, rightMotorVal;
		
		if(moveVal == 0) //If the move value is zero, but we're trying to turn use these values
		{
			leftMotorVal = -rotateVal; //The left motor is inverted so there's an actual rotation
			rightMotorVal = rotateVal;
		}
		else //If we're moving, and trying to turn use these values
		{
			//If we're trying to turn left  then get the inverse of the rotateVal, if not just use moveVal
			leftMotorVal = (rotateVal > 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
			
			//If we're trying to turn right  then get the inverse of the rotateVal, if not just use moveVal
			rightMotorVal = (rotateVal < 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
		}
		
		if(rotateVal == 0 && moveVal == 0)
		{
			//If you stop moving the joystick it'll stop the drive train
			stopDrive();
		}
		else
		{
			//Drives the motors
			leftDrive.set(leftMotorVal);
			rightDrive.set(rightMotorVal);
		}
	}
	
	public void stopDrive()
	{
		leftDrive.set(0);
		rightDrive.set(0);
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
