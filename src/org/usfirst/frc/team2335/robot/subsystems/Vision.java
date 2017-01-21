package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem
{        
    public double getDistance()
    {
    	return (27.5/12) * Robot.IMG_HEIGHT / (2 * Robot.heightPx * Math.tan(Robot.CAMERA_ANGLE));
    }
    
    public int center()
    {
    	return Robot.centerX < (Robot.IMG_WIDTH / 2) ? -1 : 1;
    }
    
    public boolean isCentered()
    {
		if(Robot.centerX < (Robot.IMG_WIDTH / 2) - 10 || Robot.centerX > (Robot.IMG_WIDTH / 2) + 10)
		{
			return false;
		}
		else
		{
			return true;
		}
    	
    }
    
    public void initDefaultCommand()
    {
        
    }
}


//Keeping this for later use incase we need it
//This finds the average of 5 values and removes any outliers in that set of data
/*
	private ArrayList<Double> numberList = new ArrayList<Double>();
		
	public double getAverage(String value)
	{    	
		clearArray();
		
		for(int i = 0; i < 5; i++)
		{
			numberList.add(Robot.centerX);
		}
		
		sortArray(0, numberList.size() - 1);
		removeOutliers();
		
		return findAverage();
	}
	
	private double findAverage()
	{
		double average = 0;
		
		for(int i = 0; i < numberList.size(); i++)
		{
			average += numberList.get(i);
		}
		
		return (average / numberList.size());
	}
	
	private void sortArray(int left, int right)
	{    	
		Collections.sort(numberList);
	}
	
	private void removeOutliers()
	{
		int counter = 0;
		
		while(counter < numberList.size())
		{
			if(counter == 0)
			{
				if(numberList.get(counter + 1) - numberList.get(counter) >= 2000
						&& numberList.get(counter + 2) - numberList.get(counter + 1) <= 2000)
				{
					numberList.remove(counter);
				}
				else
				{
					counter++;
				}
			}
			else
			{
				if(numberList.get(counter) - numberList.get(counter - 1) >= 2000)
				{
					numberList.remove(counter);
				}
				else
				{
					counter++;
				}
			}
		}
	}
	
	private void clearArray()
	{
		numberList.clear();
	}
*/