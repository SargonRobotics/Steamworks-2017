package org.usfirst.frc.team2335.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2335.robot.Robot;

public class ExampleCommand extends Command
{
	public ExampleCommand()
	{
		//Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
	}

	@Override
	protected void initialize() //Called just before this Command runs the first time
	{
		
	}

	@Override
	protected void execute() //Called repeatedly when this Command is scheduled to run

	{
		
	}

	@Override
	protected boolean isFinished() //When true is return execute() stops running
	{
		return false;
	}

	@Override
	protected void end() //Called once after isFinished returns true
	{
		
	}
	@Override
	protected void interrupted() //Called when another commands using the same subsystem is running
	{
		end();
	}
}
