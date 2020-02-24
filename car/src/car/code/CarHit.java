package car.code;

import lejos.robotics.subsumption.*;
import lejos.utility.Delay;
import lejos.hardware.motor.*;
import lejos.hardware.port.SensorPort;
import ev3.exercises.library.*;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.MovePilot;

public class CarHit implements Behavior{
    private  TouchSensor touchLeft = new TouchSensor(SensorPort.S2);
    private  TouchSensor touchRight = new TouchSensor(SensorPort.S3);
    private boolean suppressed = false;
    UltraSonicSensor uss = new UltraSonicSensor();
    Wheel wheel1 = WheeledChassis.modelWheel(Motor.C, 6).offset(-6);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.B, 6).offset(6);
    Chassis chassis = new WheeledChassis(new Wheel[]{wheel1, wheel2},WheeledChassis.TYPE_DIFFERENTIAL); 
    MovePilot pilot = new MovePilot(chassis);

	@Override
	public void action() {
		suppressed = false;
		// ultraSensor Distance
		float leftDistance = 0;
		float rightDistance = 0;
		// final distance to balance car moving 
		float ultraDistance = 0;
		
		Motor.C.setSpeed(GLOBAL.backwardSpeed);
	    Motor.B.setSpeed(GLOBAL.backwardSpeed);
	    Motor.C.backward();
	    Motor.B.backward();
	    // backward for seconds
	    Delay.msDelay(GLOBAL.backForwardTime);
		Motor.B.stop();
		Motor.C.stop();
		
//		pilot.setLinearSpeed(5.0);
//		pilot.backward();
//		Delay.msDelay(GLOBAL.backForwardTime);
		
		System.out.println("The servoAngle is: " + GLOBAL.servoAngle);
		// reboot angle to 0
	    if (GLOBAL.servoAngle == -90) {
//			servoTurnRight();
	    	Motor.A.rotate(90);
	    	GLOBAL.servoAngle += 90;
		}
	    if (GLOBAL.servoAngle == 90) {
	    	Motor.A.rotate(-90);
	    	GLOBAL.servoAngle -= 90;
	    }
		// servoAngle = 0 now
		if (GLOBAL.servoAngle == 0) {
			// to 90
			if (GLOBAL.servoAngle == 0){
//				servoTurnRight();
				Motor.A.rotate(90);
				GLOBAL.servoAngle += 90;
				rightDistance = uss.getRange();
			}
			// to -90
			if (GLOBAL.servoAngle == 90){
//				servoTurnLeft2();
				Motor.A.rotate(-180);
				GLOBAL.servoAngle -= 180;
				leftDistance = uss.getRange();
			}
			// to 0
			if (GLOBAL.servoAngle == -90){
//				servoTurnRight();
				Motor.A.rotate(90);
				GLOBAL.servoAngle += 90;
			}
			// compare which side is far, then turns to that side
			if (leftDistance <= rightDistance) {
				// car turns right
				Motor.C.rotate(360);
				// servo stays left
				Motor.A.rotate(-90);
				GLOBAL.servoAngle -= 90;
				ultraDistance = uss.getRange();
			}else if (leftDistance > rightDistance) {
				// car turns left
				Motor.B.rotate(360);
				// servo stays right
				Motor.A.rotate(90);
				GLOBAL.servoAngle += 90;
				ultraDistance = uss.getRange();
			}
			System.out.println("ultraDistance_initial=" + ultraDistance);
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		while (true) {
		    if (touchLeft.isTouched() || touchRight.isTouched()) {
		    	break;
		    }
			if (ultraDistance < GLOBAL.TOO_CLOSE) {
				// sensor on the right, should turn left
				if(GLOBAL.servoAngle == 90) {
					rover.moveDepwall(GLOBAL.ADJUSTANGLE);
					ultraDistance = uss.getRange();
					System.out.println("ultraDistance_close=" + ultraDistance);
				}
				else if(GLOBAL.servoAngle == -90) {
					rover.moveDepwall(-GLOBAL.ADJUSTANGLE);
					ultraDistance = uss.getRange();
				}
				
			}else if (ultraDistance > GLOBAL.TOO_FAR) {
				if(GLOBAL.servoAngle == 90) {
					rover.moveDepwall(-GLOBAL.ADJUSTANGLE);
					ultraDistance = uss.getRange();
					System.out.println("ultraDistance_far=" + ultraDistance);
				}
				else if(GLOBAL.servoAngle == -90) {
					rover.moveDepwall(GLOBAL.ADJUSTANGLE);
					ultraDistance = uss.getRange();
				}

			}else {
				rover.moveForward();
				ultraDistance = uss.getRange();
				System.out.println("ultraDistance_forward=" + ultraDistance);
			}
			// hit
		}
	}
	
	@Override
	public boolean takeControl() {
		// start
		return (touchLeft.isTouched() || touchRight.isTouched());
	}

	@Override
	public void suppress() {
		// stop
		suppressed = true;
	}

}
