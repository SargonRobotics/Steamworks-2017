package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoFromStation3 extends CommandGroup
{   
    public AutoFromStation3() 
    {
    	//TODO: Implement gyro to work correctly
    	    	
    	addSequential(new ReleaseGearHold());
    	addSequential(new AutoDriveAway(85)); //Passing Parameter is the distance in inches at which the Ultrasonic stops the motor.
    	addSequential(new AutoTurn(1));  //Value is which direction the motor turns.
    	addSequential(new AutoDriveTo());
    }
}
