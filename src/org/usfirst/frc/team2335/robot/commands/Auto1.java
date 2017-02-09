package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto1 extends CommandGroup
{ 
	//TODO: Give the command a better name
	//And just so you know before I fixed your formatting is was absolute balls
    public  Auto1() 
    {
    	//TODO: get correct time and distance values for the following:
    	
    	//These values here to me look meaningless
    	//TODO: add comments on what the passing parameters mean
    	addSequential(new AutoDrive(10)); 
    	
    	//More important TODO: fix this error
    	addSequential(new AutoTurn()); //TODO: find out turn directions
    	addSequential(new AutoDrive(10));
    }  
}
