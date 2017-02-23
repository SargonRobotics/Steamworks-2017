package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation2 extends CommandGroup
{    
    public AutoFromStation2() 
    {
    	//TODO: get correct time and distance values. Current value is a placeholder
    	
    	//TODO: Find strafe value
    	
    	addSequential(new AutoStrafe());
    	addSequential(new AutoDriveTo());    //passing  is the distance in inches at which the Ultrasonic stops the motor.
    }
}
