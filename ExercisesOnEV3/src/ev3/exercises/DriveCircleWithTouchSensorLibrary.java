package ev3.exercises;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;

import ev3.exercises.library.*;

public class DriveCircleWithTouchSensorLibrary
{
    public static void main(String[] args)
    {
        TouchSensor touchSensor = new TouchSensor(SensorPort.S1);

        System.out.println("Drive Circle (2)\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();  // make sound when ready.

        Button.waitForAnyPress();

       // create two motor objects to control the motors.
       UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
       UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

       // set motors to different power levels. Adjust to get a circle.
       motorB.setPower(70);
       motorC.setPower(30);

       // wait doing nothing for touch sensor to stop driving.
       while (!touchSensor.isTouched()) {}

       // stop motors with brakes on.
       motorB.stop();
       motorC.stop();

       // free up resources.
       motorB.close();
       motorC.close();
       touchSensor.close();

       Sound.beepSequence(); // we are done.
   }
}