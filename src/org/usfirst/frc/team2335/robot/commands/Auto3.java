package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto3 extends CommandGroup {
    
    public  Auto3() 
    {
    	//TODO: Get correct time and distance values for the following
    addSequential(new AutoDrive(69)); 
    addSequential(new AutoTurn(-2));
    addSequential(new AutoDrive(69));

    }
}
