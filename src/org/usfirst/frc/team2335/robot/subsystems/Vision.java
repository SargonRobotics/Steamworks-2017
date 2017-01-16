package org.usfirst.frc.team2335.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem
{
	private ArrayList<Double> numberList = new ArrayList<Double>();

    public double getArea()
    {
    	return Robot.area;
    }
    
    public double getCenterX()
    {
    	return Robot.centerX;
    }
    
    public double getCenterY()
    {
    	return Robot.centerY;
    }
    
    public double getAverage(String value)
    {    	
    	clearArray();
    	
    	for(int i = 0; i < 5; i++)
    	{
    		switch(value)
    		{
    		case "area": numberList.add(getArea()); break;
    		case "centerX": numberList.add(getCenterX()); break;
    		case "centerY": numberList.add(getCenterY()); break;
    		}
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
    
    public void initDefaultCommand()
    {
        
    }
}

