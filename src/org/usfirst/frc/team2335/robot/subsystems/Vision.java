package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem
{      
	//Value for finding the center of the camera (width resolution divided by 2)
	private int center = Robot.IMG_WIDTH / 2;
	
	//TARGET_FEET is out target width in feet, CAMERA_ANGLE is our full view angle
	//And FULL_VIEW_PX is out width resolution
	private final float TARGET_FEET = 1.25f, CAMERA_ANGLE = 46.46f, FULL_VIEW_PX = Robot.IMG_WIDTH;
	
	//This is the distance we want to be from the target it feet
	//TODO: Find appropriate distance from target
	private final float wantedDistanceInFeet = 12.0f;
	
    public float getDistance()
    {
    	//Use floats, they're way more accurate (AKA: doubles are the devil)
    	float targetWidthPx, feetPxRatio, fullFiewInFeet, halfOfView, distance;	
    	
    	targetWidthPx = Robot.targetWidthPx; //Gets the width of the reflective tape in pixels from the pipeline
    	feetPxRatio = TARGET_FEET / targetWidthPx; //Ratio for getting our full view in ft
    	fullFiewInFeet = feetPxRatio * FULL_VIEW_PX; //Our full view of the camera in ft
    	
    	halfOfView = fullFiewInFeet / 2; //Value used with trig to find distance
    	
    	//Math.tan uses radians, so we convert our angle value to radians
    	//To find distance we use trig, knowing one of the legs, and an angle
    	//We use tangent to find the other leg of the right triangle
    	distance = (float) (halfOfView / Math.tan(Math.toRadians(CAMERA_ANGLE / 2)));
    	
    	//TODO: Add a function that factors out our common error %
    	
    	return distance;
    }
    
    //Determines whether or not the robot is too much to the left, or the right of the tape
    public int center()
    {
    	if(Robot.centerX < center)
    	{
    		//Returns 1 if the robot needs to move right
    		return 1;
    	}
    	else
    	{
    		//Returns -1 if the robot needs to move left
    		return -1;
    	}
    }
    
    //Determines if the robot is centered, stops the command
    public boolean isCentered()
    {
    	//+5 and -5 set a range for the center to be in, since it will most likely not be exactly in the center
		if(Robot.centerX < center + 5 && Robot.centerX > center - 5)
		{
			return true;
		}
		else
		{
			return false;
		}
    	
    }
    
    //Determines whether or not the robot is too far forward, or back from the target
    public int position()
    {
    	if(getDistance() < center)
    	{
    		//Returns 1 if the robot needs to move forward
    		return 1;
    	}
    	else
    	{
    		//Returns -1 if the robot needs to move backwards
    		return -1;
    	}
    }
    
    public boolean isPositioned()
    {
    	///+5 and -5 set a range for the distance to be in, since it will most likely not be exactly the right distance
    	if(getDistance() < wantedDistanceInFeet + 5 && getDistance() > wantedDistanceInFeet -5)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public void initDefaultCommand()
    {
        
    }
}