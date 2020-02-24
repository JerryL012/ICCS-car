package ev3.exercises;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveForward2
{
    public static void main(String[] args)
    {
        System.out.println("Drive Forward\nand Stop\n");
        System.out.println("Press any key to start");
        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
        UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

        // set motors to 100% power.
        motorB.setPower(100);
        motorC.setPower(100);

        // wait 5 seconds.
        Delay.msDelay(5000);

        // stop motors with brakes on. 
        motorB.stop();
        motorC.stop();

        // free up motor resources. 
        motorB.close(); 
        motorC.close();
 
        Sound.beepSequence(); // we are done.
    }
}