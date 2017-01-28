package org.usfirst.frc.team2335.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasound extends Subsystem {

   Ultrasonic ultra = new Ultrasonic(1,1);
   
   public void robotInit() {
	   ultra.setAutomaticMode(true);
   }

   public double getRange() {
	   return ultra.getRangeInches();
   }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

