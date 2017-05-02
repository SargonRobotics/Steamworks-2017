package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasound extends Subsystem
{

	Ultrasonic frontUltra, backUltra;
	
	public Ultrasound()
	{
		frontUltra = new Ultrasonic(Robot.FRONT_PING, Robot.FRONT_ECHO);
		backUltra = new Ultrasonic(Robot.BACK_PING, Robot.BACK_ECHO);
		
		frontUltra.setAutomaticMode(true);
		backUltra.setAutomaticMode(true);
	}
	
	public double getFrontRange()
	{
		return frontUltra.getRangeInches();
	}
	
	public double getBackRange()
	{
		return backUltra.getRangeInches();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

