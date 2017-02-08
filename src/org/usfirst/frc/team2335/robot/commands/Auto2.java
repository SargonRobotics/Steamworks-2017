package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto2 extends CommandGroup {
    
    public  Auto2() 
    {
    	//TODO: get correct time and distance values
      addSequential(new AutoStrafe(2)); 
      addSequential(new AutoDrive(2));
    
   
    }
}
