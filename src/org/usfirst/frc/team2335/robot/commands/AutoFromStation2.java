package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation2 extends CommandGroup
{    
    public AutoFromStation2() 
    {
    	//TODO: Implement gyro to work correctly
    	
    	addSequential(new ReleaseGearHold());
    	addSequential(new AutoDriveTo()); 
    }
}
