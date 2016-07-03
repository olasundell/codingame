import java.awt.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.List;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class MarsLanderPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of points used to draw the surface of Mars.
	    List<Point> ground = new ArrayList<>();
	    int flatStartX = Integer.MAX_VALUE;
	    int flatEndX = Integer.MAX_VALUE;
	    int landingHeight = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int LAND_X = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int LAND_Y = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
	        ground.add(new Point(LAND_X, LAND_Y));
	        if (i != 0 && ground.get(i - 1).y == LAND_Y) {
		        flatStartX = i - 1;
		        landingHeight = LAND_Y;
	        } else if (i > flatStartX && ground.get(i - 1).y != LAND_Y) {
		        flatEndX = i -1;
	        }
        }

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int HS = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int VS = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int F = in.nextInt(); // the quantity of remaining fuel in liters.
            int R = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int P = in.nextInt(); // the thrust power (0 to 4).

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

	        if (Y > (landingHeight + VS*VS)) {
		        System.out.println("0 2"); // R P. R is the desired rotation angle. P is the desired thrust power.
	        } else {
		        System.err.println("Throttling up");
		        System.out.println("0 4");
	        }

//	        if (Math.abs(VS) < 15) {
//	        } else {
//		        System.out.println("0 4");
//	        }
        }
    }
}