package org.usfirst.frc.team2335.robot.triggers;

import org.usfirst.frc.team2335.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class Axis extends Trigger
{
    //Variables to store joystick, and button values
	private Joystick _controller;
	private int _axis;
	
	//Sets the variables to return
	public Axis(Joystick controller, int axis)
	{
		_controller = controller;
		_axis = axis;
	}
	
	//If the controller value isn't at 0, then it's seen as active
	//In the OI file it uses .whenActive() or .whileActive()
	//That means that command will run either once or while this get method is returning true
    public boolean get()
    {
    	if(OI.deadzone(_controller.getRawAxis(_axis), 1) > 0 || OI.deadzone(_controller.getRawAxis(_axis), 1) < 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
