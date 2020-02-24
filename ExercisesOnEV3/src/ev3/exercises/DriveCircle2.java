package ev3.exercises;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class DriveCircle2
{ 
    public static void main(String[] args)
    {
        EV3TouchSensor sensor1 = new EV3TouchSensor(SensorPort.S1);//S1 matters!
        SampleProvider touchSP = sensor1.getTouchMode();

        System.out.println("Drive Circle\nand Stop\n");
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
       while (!isTouched(touchSP)) {}

       // stop motors with brakes on.
       motorB.stop();
       motorC.stop();

       // free up resources.
       motorB.close();
       motorC.close();
       sensor1.close();

       Sound.beepSequence(); // we are done.
   }
   
   // method to read touch sensor and return true or false if touched.
   private static boolean isTouched(SampleProvider sp)
   {
       float [] sample = new float[sp.sampleSize()];
    
       sp.fetchSample(sample, 0);

       if (sample[0] == 0)
           return false;
       else
           return true;
   }
 }