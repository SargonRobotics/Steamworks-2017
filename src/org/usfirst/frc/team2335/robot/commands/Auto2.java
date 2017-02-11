package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto2 extends CommandGroup
{    
	//TODO: Give the command a better name
	//And just so you know before I fixed your formatting is was absolute balls
    public Auto2() 
    {
    	//TODO: get correct time and distance values. Current values are placeholders.
    	
    	//These values here to me look meaningless
    	//TODO: add comments on what the passing parameters mean
    	addSequential(new AutoStrafe()); //What does the 2 in here mean? You don't have a constructor with a passing parameter
    	addSequential(new AutoDrive(0));   //value
   
}
