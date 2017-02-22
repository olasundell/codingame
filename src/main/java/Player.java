import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
	    new Player().solve(in);
    }

	private void solve(Scanner in) {
		int N = in.nextInt(); // the number of points used to draw the surface of Mars.

		Ground ground = new Ground();

		for (int i = 0; i < N; i++) {
		    int LAND_X = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
		    int LAND_Y = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
			ground.add(new Point(LAND_X, LAND_Y));
		}

		ground.findLandingSite();

		System.err.println("Flat start: " + ground.flatStartX + " flat end: " + ground.flatEndX);

		// game loop
		while (true) {
			State state = new State();
		    state.x = in.nextInt();
		    state.y = in.nextInt();
		    state.horizontalSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
		    state.verticalSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
		    int F = in.nextInt(); // the quantity of remaining fuel in liters.
		    state.angle = in.nextInt(); // the rotation angle in degrees (-90 to 90).
		    state.power = in.nextInt(); // the thrust power (0 to 4).

		    // Write an action using System.out.println()
		    // To debug: System.err.println("Debug messages...");
			int angle = determineAngle(ground, state);
			int power = determineThrottle(ground, state);
			System.err.println(state.toString());
			System.out.println(angle + " " + power);
		}
	}

	private int determineAngle(Ground ground, State state) {
    	// state.x + horizontalSpeed?
		if (Math.abs(state.angle) > 45) {
			return state.angle - (int) (15 * Math.signum(state.angle));
		}
		if (Math.abs(state.horizontalSpeed) > 50) {
			// we need to decrease it
			int i = (int) (30 * Math.signum(state.horizontalSpeed));
			System.err.println("Decreasing speed, setting angle to " + i);
			return i;
		}

		if (ground.inTheBox(state)) {
			System.err.println("In the box");
			if (Math.abs(state.horizontalSpeed) > 10) {
				if (Math.abs(state.angle) < 45) {
					System.err.println("In the box and horiz speed too high, increasing angle");
					return state.angle += 15 * Math.signum(state.horizontalSpeed);
				}
			}
		} else {
			if (Math.abs(state.horizontalSpeed) <= 20) {
				System.err.println("Too slow, increasing horiz speed");
				return 30 * (state.x < ground.flatStartX ? -1 : 1);
			}
		}
//		} else if (state.x < ground.flatStartX) {
//    		if (Math.abs(state.horizontalSpeed >= 20) {
//    			if (state.angle > 0) {
//    				state.angle = 30;
//			    }
//		    } else if (state.angle >= -45) {
//			    return state.angle -= 15;
//		    } else {
//    			return state.angle;
//		    }
//		} else if (state.x > ground.flatEndX) {
//			if (state.horizontalSpeed <= -20) {
//				if (state.angle < 0) {
//					state.angle = 30;
//				}
//			} else if (state.angle <= 45) {
//				return state.angle += 15;
//			} else {
//				return state.angle;
//			}
//
//		}

	    return 0;
    }

    private int determineThrottle(Ground ground, State state) {
    	if (Math.abs(state.angle) > 45) {
    		return 0;
	    }

	    // do suicide burn calculation including peaks
	    if (state.angle != 0 ||
			    state.verticalSpeed < -20 ||
			    (state.y < ground.flatY + 10 && !ground.inTheBox(state)))  {
    		return 4;
	    } else {
		    return 3;
	    }
    }

    public static class State {
    	int x;
    	int y;
    	int angle;
    	int horizontalSpeed;
    	int verticalSpeed;
    	int power;

	    @Override
	    public String toString() {
		    return "State{" +
				    "x=" + x +
				    ", y=" + y +
				    ", angle=" + angle +
				    ", horizontalSpeed=" + horizontalSpeed +
				    ", verticalSpeed=" + verticalSpeed +
				    ", power=" + power +
				    '}';
	    }
    }

    public static class Ground {
	    private final List<Point> points = new ArrayList<>();
	    private int flatStartX;
	    private int flatEndX;
	    private int flatY;

	    public void add(Point p) {
	    	points.add(p);
	    }

	    public Point get(int idx) {
	    	return points.get(idx);
	    }

	    public List<Point> findPeaks(State state) {
	    	// determine direction to land
	    	// find all non-landing points between current x and closest landing point


		    return Collections.emptyList();
	    }

	    public void findLandingSite() {
	    	int y = Integer.MAX_VALUE;
	    	int same = 0;

	    	for (int i = 1 ; i < points.size() ; i++) {
			    Point current = points.get(i);
			    Point prev = points.get(i - 1);
			    if (current.y == prev.y) {
			    	flatStartX = prev.x;
			    	flatEndX = current.x;
			    	flatY = prev.y;
			    	break;
			    }
		    }
//		    if (i != 0 && ground.get(i - 1).y == LAND_Y) {
//			    flatStartX = i - 1;
//			    landingHeight = LAND_Y;
//		    } else if (i > flatStartX && ground.get(i - 1).y != LAND_Y) {
//			    flatEndX = i -1;
//		    }
	    }
	    public boolean inTheBox(State state) {
	    	return state.x >= flatStartX && state.x <= flatEndX;
	    }

	    @Override
	    public String toString() {
		    return "Ground{" +
				    "points=" + points +
				    ", flatStartX=" + flatStartX +
				    ", flatEndX=" + flatEndX +
				    ", flatY=" + flatY +
				    '}';
	    }
    }

}