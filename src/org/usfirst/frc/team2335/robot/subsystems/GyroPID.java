package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroPID extends PIDSubsystem
{
	 ADXRS450_Gyro gyro;
	 
	 double storedAngle = 0;
	 	
	private static final double kUltimateGain = 0.363, kOscillationPeriod = 0.02;
	
	//Values calculated using the Zeigler-Nichols theory
	private static final double kP = 0.6 * kUltimateGain;
	private static final double kI = 0.5 * kOscillationPeriod;
	private static final double kD = 0.125 * kOscillationPeriod;
	 
    // Initialize your subsystem here
    public GyroPID()
    {   
    	super("Gyro PID", kP, kI, kD, 0.0, kOscillationPeriod * 0.85);
    	
    	setAbsoluteTolerance(5);
    	
    	gyro = new ADXRS450_Gyro();
    }

    public void init()
    {
    	gyro.calibrate();
    }
    
    public void reset()
    {
    	gyro.reset();
    }
    
    public double getAngle()
    {	
    	return getFiltedValue();
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput()
    {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getFiltedValue();
    }
    
    private double getFiltedValue()
    {
    	storedAngle = Math.abs(gyro.getRate()) < 0.5 ? storedAngle : gyro.getAngle();
    	
    	return storedAngle;
    }

    protected void usePIDOutput(double output)
    {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	Robot.driveTrain.drive(0, -output);
    	SmartDashboard.putString("PID Output: ", Double.toString(getAngle()) + ", " + Double.toString(output));
    }
}
