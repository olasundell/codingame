import java.util.Scanner;

/*
The program:
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
Allotted response time to first output line ≤ 1s.
Response time between two output lines ≤ 100ms
 */

class ApuBasicPlayer {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int width = in.nextInt(); // the number of cells on the X axis
		in.nextLine();
		int height = in.nextInt(); // the number of cells on the Y axis
		in.nextLine();

		byte[][] board = new byte[height][width];

		for (int i = 0; i < height; i++) {
			String line = in.nextLine(); // width characters, each either 0 or .
			System.err.println(line);
			board[i] = line.getBytes();
		}

		for (int i = 0 ; i < height ; i++) {
			for (int j = 0 ; j < width ; j++) {
				if (board[i][j] == '.') {
					continue;
				}

				String str = j + " " + i;

				int count = 1;

				while (count < (width - 1 - j) && board[i][j + count] == '.') {
					count++;
				}

				if (j + count <= width - 1) {
					str += " " + (j + count) + " " + i;
				} else {
					str += " -1 -1";
				}

				count = 1;

				while (count < (height - 1 - i) && board[i + count][j] == '.') {
					count++;
				}

				if (i + count <= height - 1) {
					str += " " + j + " " + (i + count);
				} else {
					str += " -1 -1";
				}

				System.out.println(str);
			}
		}



		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
	}
}
