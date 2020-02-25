package car.code;

public class GLOBAL {
	// Target 0.3
	// Range +- 0.05
	// TOo close 0.25
	// too far 0.35
	
	// 10% +-
	// 1. target 0.3, 0.27~0.33
	// 2. target 0.2, 0.18~0.22
	// 3. target 0.15, 0.135~0.165, speed 300
	// test 0.8
	
	// 1. speed 500, turning speed 100 -- 200 -- 300
	
	
	// sensor range: 0.1, 0.09~0.11 X
	// sensor range: 0.15 0.135~0.165
	// 0.2, 0.18~0.22
	// 0.25, 0.225~0.275
	// 0.3, 0.27~0.33
	// 0.35, 0.315~0.385
	// 0.4, 0.36~0.44
	
	
    // balance forward
    static final double TOO_CLOSE = 0.27;
    static final double TOO_FAR = 0.33;
    public static int forwardSpeed = 500;
    
    // Angle of the servo motor at this moment
	static int servoAngle = 0;
	
	// car move
	public static int initialSpeed = 500;		// in 1000
	static int backwardSpeed = 330;				// in 1000
	static final int backForwardTime = 800;		// 0.9s
	static final int ADJUSTANGLE = 90; 		// adjust angle
	
	// UltraSensor
	static final double OPTIMAL_RANGE = 0.18; 
}
