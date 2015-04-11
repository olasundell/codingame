import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class SkynetPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // the length of the road before the gap.
        int G = in.nextInt(); // the length of the gap.
        int L = in.nextInt(); // the length of the landing platform.

        // game loop
        while (true) {
            int S = in.nextInt(); // the motorbike's speed.
            int X = in.nextInt(); // the position on the road of the motorbike.

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

	        if (X == R - 1) {
		        System.out.println("JUMP");
	        } else if (X < R -1){
		        if (S <= G) {
			        System.out.println("SPEED"); // A single line containing one of 4 keywords: SPEED, SLOW, JUMP, WAIT.
		        } else if (S > G + 1) {
			        System.out.println("SLOW");
		        } else {
			        System.out.println("WAIT");
		        }
	        } else {
		        System.out.println("SLOW");
	        }
        }
    }
}