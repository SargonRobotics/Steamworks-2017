package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto1 extends CommandGroup {
	
	
    
    public  Auto1() 
    {
    	//TODO: get correct time and distance values for the following:
    	addSequential(new AutoDrive(10)); 
    	addSequential(new AutoTurn()); //TODO: find out turn directions
    	addSequential(new AutoDrive(10));
    }
    
}
