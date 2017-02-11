package org.usfirst.frc.team2335.robot.triggers;

import org.usfirst.frc.team2335.robot.OI;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class Axis extends Trigger
{
	//Constants
	public static final double DEADZONE = 0.2;
	
	//Axes
	public static final int MOVE = 1, ROTATE = 0;
	
	//Motors
	public static final int LEFTDRIVE = 1, RIGHTDRIVE = 0, ARM = 8;

    //Variables to store joystick, and button values
	private int _axis;
	
	//Sets the variables to return
	public Axis(int axis)
	{
		_axis = axis;
	}
	
	//If the controller value isn't at 0, then it's seen as active
	//In the OI file it uses .whenActive() or .whileActive()
	//That means that command will run either once or while this get method is returning true
    public boolean get()
    {
    	if(OI.getAxis(_axis, 1) > 0 || OI.getAxis(_axis, 1) < 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
