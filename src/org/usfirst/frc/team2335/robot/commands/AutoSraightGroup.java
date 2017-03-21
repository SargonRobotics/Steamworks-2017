package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSraightGroup extends CommandGroup
{
    public AutoSraightGroup()
    {
        addParallel(new ReleaseGearHold());
        addParallel(new AutoStraight());
    }
}
