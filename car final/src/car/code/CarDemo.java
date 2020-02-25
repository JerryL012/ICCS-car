package car.code;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
/**
 * Sensor port: leftTouch port2, rightTouch port3, angle port1, ultraSonic port4
 * Motor: wheels B and C, rotator A.
 */

public class CarDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Behavior b1 = new CarMoveForward();
		Behavior b2 = new CarHit();
		Behavior [] bArray = {b1, b2};
		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
	}

}
