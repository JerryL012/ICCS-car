package car.code;

import lejos.robotics.subsumption.*;
import lejos.utility.Delay;
import lejos.hardware.motor.*;

public class CarMoveForward implements Behavior{
	private boolean suppressed = false;

	@Override
	public void action() {
	    // set speed here
	    suppressed = false;
	    Motor.D.setSpeed(GLOBAL.initialSpeed);
	    Motor.C.setSpeed(GLOBAL.initialSpeed);
	    Motor.D.forward();
	    Motor.C.forward();	    
	    
	    while(!suppressed) {
	    	Thread.yield();
	    }
	}

	@Override
	public boolean takeControl() {
		// start
		return true;
	}
	
	@Override
	public void suppress() {
		// stop
		suppressed = true;
	}
	
}
