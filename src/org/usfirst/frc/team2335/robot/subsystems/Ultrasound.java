package org.usfirst.frc.team2335.robot.subsystems;

import org.usfirst.frc.team2335.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasound extends Subsystem {

   Ultrasonic ultra = new Ultrasonic(Robot.ULTRASONIC_TRIGGER_PULSE_INPUT, Robot.ULTRASONIC_ECHO_PULSE_OUTPUT);
   
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

