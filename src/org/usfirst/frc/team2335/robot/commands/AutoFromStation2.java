package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation2 extends CommandGroup
{    
    public AutoFromStation2() 
    {
    	//TODO: get correct time and distance values. Current value is a placeholder
    	addSequential(new AutoStrafe());
    	addSequential(new AutoDrive(0));    //passing  is the distance in inches at which the Ultrasonic stops the motor.
    }
}
