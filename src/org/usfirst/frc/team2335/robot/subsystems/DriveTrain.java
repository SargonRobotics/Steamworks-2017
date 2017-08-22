package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		double leftMotorVal, rightMotorVal;
		
		if(moveVal == 0) //If the move value is zero, but we're trying to turn use these values
		{
			leftMotorVal = -rotateVal; //The left motor is inverted so there's an actual rotation
			rightMotorVal = rotateVal;
		}
		else if(moveVal > 0) //If we're moving forwards, and trying to turn use these values
		{
			//If we're trying to turn left  then get the inverse of the rotateVal, if not just use moveVal
			leftMotorVal = (rotateVal > 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
			
			//If we're trying to turn right  then get the inverse of the rotateVal, if not just use moveVal
			rightMotorVal = (rotateVal < 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
		}
		else //If we're moving backwards, and trying to turn use these values
		{
			//If we're trying to turn left  then get the inverse of the rotateVal, if not just use moveVal
			leftMotorVal = (rotateVal < 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
			
			//If we're trying to turn right  then get the inverse of the rotateVal, if not just use moveVal
			rightMotorVal = (rotateVal > 0) ? (moveVal * (1 - Math.abs(rotateVal))) : moveVal;
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
	
	public void driveToAuto()
	{
		drive(Robot.frontUltraPID.pidOutput, Robot.turnCorrectionPID.pidOutput);
		SmartDashboard.putString("GyroPID", Double.toString(Robot.gyroPID.pidOutput));
		SmartDashboard.putString("GyroPID-Set", Double.toString(Robot.gyroPID.getSetpoint()));
		SmartDashboard.putString("UltraPID", Double.toString(Robot.frontUltraPID.pidOutput));
	}
	
	public void driveAwayAuto()
	{
		drive(Robot.backUltraPID.pidOutput, Robot.turnCorrectionPID.pidOutput);
		SmartDashboard.putString("GyroPID", Double.toString(Robot.gyroPID.pidOutput));
		SmartDashboard.putString("GyroPID-Set", Double.toString(Robot.gyroPID.getSetpoint()));
		SmartDashboard.putString("UltraPID", Double.toString(Robot.backUltraPID.pidOutput));
	}
	
	//Test adding turnCompensation to the Robot.turnCorrectionPID.pidOutput if it's still going off a little
	
	public void turnAuto()
	{
		drive(0, Robot.gyroPID.pidOutput);
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
