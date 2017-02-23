package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation3 extends CommandGroup
{   
    public AutoFromStation3() 
    {
    	//TODO: Get correct time and distance values for the following. Current values are placeholders
    	
    	//TODO: Test
    	
    	addSequential(new AutoDriveAway(98)); //Passing Parameter is the distance in inches at which the Ultrasonic stops the motor.
    	addSequential(new AutoTurn(0));  //Value is which direction the motor turns.
    	addSequential(new AutoDriveTo());
    }
}
