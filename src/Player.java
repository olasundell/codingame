import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        // game loop
        while (true) {
            String BOMB_DIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

	        X0 += translateX(BOMB_DIR);
	        Y0 += translateY(BOMB_DIR);

            System.out.println(X0 + " " + Y0); // the location of the next window Batman should jump to.
        }
    }

	protected static int translateY(String dir) {
		if (dir.contains("U")) {
			return -1;
		} else if (dir.contains("D")) {
			return 1;
		}

		return 0;
	}

	protected static int translateX(String dir) {
		if (dir.contains("L")) {
			return -1;
		} else if (dir.contains("R")) {
			return 1;
		}

		return 0;

	}
}