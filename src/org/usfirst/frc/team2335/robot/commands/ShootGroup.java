package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootGroup extends CommandGroup {
    
    public ShootGroup()
    {
    	DriverStation.reportWarning("Running", true);
    	//Will runs these commands one after another
        addSequential(new StartShooter());
        addSequential(new FeedShooter());
    }
}
