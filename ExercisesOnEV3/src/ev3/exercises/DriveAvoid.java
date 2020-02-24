package ev3.exercises;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import ev3.exercises.library.*;

public class DriveAvoid 
{ 
    public static void main(String[] args)
    {
       TouchSensor         touch = new TouchSensor(SensorPort.S1);
       UltraSonicSensor    ultra = new UltraSonicSensor(SensorPort.S4);
       GyroSensor            gyro = new GyroSensor(SensorPort.S2);
        
       System.out.println("Drive and Avoid\n");
       System.out.println("Press any key to start");
       
       Button.LEDPattern(4);    // flash green led and 
       Sound.beepSequenceUp();  // make sound when ready.

       Button.waitForAnyPress();
        
       // create two motor objects to control the motors.
       UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
       UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

       motorB.setPower(100);
       motorC.setPower(100);

       // drive waiting for touch sensor or escape key to stop driving.
       while (!touch.isTouched() && Button.ESCAPE.isUp()) 
       {
           Lcd.clear(6);
           Lcd.print(6, "range=%.3f", ultra.getRange());
           
           // watch for obstacle.
           if (ultra.getRange() < .1)
           {
               // Set gyro angle to zero.
               gyro.reset();

               Lcd.clear(7);
               Lcd.print(7, "angle=%d", gyro.getAngle());
               Delay.msDelay(50);

               // start rotation around current location.
               motorB.setPower(-100);
               motorC.setPower(+100);
               
               // wait for 90 degrees of rotation
               while (Math.abs(gyro.getAngle()) < 90 && !touch.isTouched() && Button.ESCAPE.isUp())
               {
                   Lcd.clear(7);
                   Lcd.print(7, "angle=%d", gyro.getAngle());
                   Delay.msDelay(50);
               }

               // back to straight driving.
               motorB.setPower(100);
               motorC.setPower(100);
           }
           
           Delay.msDelay(50);
       }
       
       // stop motors with brakes on.
       motorB.stop();
       motorC.stop();

       // free up resources.
       motorB.close();
       motorC.close();
       touch.close();
       ultra.close();
       gyro.close();
       
       Sound.beepSequence(); // we are done.
   }
 }