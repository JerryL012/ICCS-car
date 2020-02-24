package car.code;

public class GLOBAL {
    // balance forward
    static final double TOO_CLOSE = 0.15;
    static final double TOO_FAR = 0.3;
    public static int forwardSpeed = 300;
    
    // Angle of the servo motor at this moment
	static int servoAngle = 0;
	
	// car move
	public static int initialSpeed = 200;		// in 1000
	static int backwardSpeed = 200;				// in 1000
	static final int backForwardTime = 900;		// 0.9s
	static final int ADJUSTANGLE = 100; 		// adjust angle
	
	// UltraSensor
	static final double OPTIMAL_RANGE = 0.18; 
}
