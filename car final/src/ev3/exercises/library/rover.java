package ev3.exercises.library;

import lejos.hardware.motor.Motor;
import car.code.*;


public class rover {
	private static int speed = GLOBAL.initialSpeed;
	//global variable speed, should be the same as the gobal class
	public rover(int speed) {
		this.speed = speed;
		Motor.D.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}
	
	//robot moveforward
	public static void moveForward() {
		 Motor.D.setSpeed(GLOBAL.forwardSpeed);
		 Motor.C.setSpeed(GLOBAL.forwardSpeed);
		 Motor.D.forward();
		 Motor.C.forward();
	}
	
	//robot difference speed of motor will lead the robot turn left or right
	//if speedDifference is positive, turn left
	public static void moveDepwall(int speedDifference) {
		 Motor.D.setSpeed(speed + speedDifference);
		 Motor.C.setSpeed(speed - speedDifference);
		 Motor.D.forward();
		 Motor.C.forward();
	}
}
