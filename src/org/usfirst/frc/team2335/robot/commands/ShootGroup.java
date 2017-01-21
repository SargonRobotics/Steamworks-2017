package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootGroup extends CommandGroup {
    
    public ShootGroup()
    {
        addSequential(new StartShooter());
        addSequential(new FeedShooter());
        addSequential(new StopShooter());
    }
}
