package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation1 extends CommandGroup
{ 
	//And just so you know before I fixed your formatting is was absolute balls
    public AutoFromStation1() 
    {
    	//TODO: get correct time and distance values for the following. Current values are placeholders.
    	addSequential(new AutoDrive(0)); //passing  is the distance in inches at which the Ultrasonic stops the motor.
    	addSequential(new AutoTurn(0)); //TODO: find out turn directions. Value is which direction the motor turns.
    	addSequential(new AutoDrive(0));
    }  
}