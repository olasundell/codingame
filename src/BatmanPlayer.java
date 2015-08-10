import java.util.*;
import java.io.*;
import java.math.*;

/*
 * The program:
 The game is played on a rectangular grid with a given size. Some cells contain power nodes. The rest of the cells are empty.

 The goal is to find the horizontal and vertical neighbors of each node.

 To do this, you must find each (x1,y1) coordinates containing a node, and display the (x2,y2) coordinates of the next node to the right, and the (x3,y3) coordinates of the next node to the bottom within the grid.

 If a neighbor does not exist, you must output the coordinates -1 -1 instead of (x2,y2) and/or (x3,y3).

 YOU WIN WHEN ALL NODES HAVE BEEN CORRECTLY COMPUTED.

 You lose if:
 You give an incorrect neighbor for a node.
 You give the neighbors for an empty cell.
 You compute the same node twice.
 You forget to compute the neighbors of a node.

 Notes:
 Debug mode is available from the settings panel (the dented wheel)
 You can zoom/unzoom with the mouse wheel and move using drag'n drop (useful for large grids)
 EXAMPLE: THERE ARE THREE NODES IN A 2 BY 2 GRID. THE CELL AT (1,1) IS EMPTY.

 00
 0.

 TURN 1
 THE NODE AT (0,0) HAS 2 NEIGHBORS.
 0 0 1 0 0 1

 TURN 2
 THE NODE AT (1,0) HAS NO NEIGHBORS.
 1 0 -1 -1 -1 -1

 TURN 3
 THE NODE AT (0,1) HAS NO NEIGHBORS.
 0 1 -1 -1 -1 -1
 THE APU'S WEAPONS ARE ONLINE. YOU HAVE WON.
 The program must first read the initialization data from standard input. Then, provide to the standard output one line per instruction.

 Don’t forget to run the tests by launching them from the “Test cases” window. You do not have to pass all tests to enter the leaderboard. Each test you pass will earn you some points (for example 10%).

 Warning: the tests provided are similar to the validation tests used to compute the final score but remain different. This is a "harcoding" prevention mechanism. Harcoded solutions will not get any points.
 INPUT DATA:
 Line 1: one integer width for the number of cells along the x axis.
 Line 2: one integer height for the number of cells along the y axis.
 Next height lines: A string  line  containing  width  characters. A dot . represents an empty cell. A zero 0 represents a cell containing a node.
 OUTPUT DATA:
 One line per node. Six integers on each line:   x1  y1  x2  y2  x3  y3   to state that there is a node at position (x1,y1) and that this node's right neighbor is at (x2,y2) and its bottom neighbor is at (x3,y3).

 If there is no neighbor, the coordinates should be -1 -1.
 CONSTRAINTS:
 0 < width ≤ 30
 0 < height ≤ 30
 0 ≤ x1 < width
 0 ≤ y1 < height
 -1 ≤ x2, x3 < width
 -1 ≤ y2, y3 < height
 Alloted response time to first output line ≤ 1s.
 Response time between two output lines ≤ 100ms
 **/
class BatmanPlayer {

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