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
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.

	    Map<Integer, Tile> tiles = setupTiles();

	    Tile[][] board = new Tile[H][W];

        in.nextLine();
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
	        String[] line = LINE.split(" ");
	        board[i] = new Tile[W];
	        for (int j = 0 ; j < line.length ; j++) {
		        final int key = Integer.valueOf(line[j]);
		        board[i][j] = tiles.get(key);
		        System.err.print(key + " ");
	        }
	        System.err.println();
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        in.nextLine();


        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            in.nextLine();

	        Direction moveDirection;

	        final Tile tile = board[YI][XI];
	        System.err.print("Tile is type " + tile.id + ", ");
	        switch (POS) {
	        case "TOP":
		        System.err.println("came from the north");
		        moveDirection = tile.entriesAndExits.get(Direction.NORTH);
		        break;
	        case "LEFT":
		        System.err.println("came from the west!");
		        moveDirection = tile.entriesAndExits.get(Direction.WEST);
		        break;
	        case "RIGHT":
		        System.err.println("came from the east!");
		        moveDirection = tile.entriesAndExits.get(Direction.EAST);
		        break;
	        default:
		        System.err.println("came from nowhere!");
		        throw new IllegalStateException("Came from nowhere!");
	        }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

	        String output;

	        System.err.println("Moving to the " + moveDirection.name());

	        switch (moveDirection) {
	        case SOUTH:
		        output = XI + " " + (YI + 1);
				break;
	        case EAST:
		        output = (XI + 1) + " " + YI;
		        break;
	        case WEST:
		        output = (XI - 1) + " " + YI;
		        break;
	        default:
		        System.err.println("Going nowhere!");
		        throw new IllegalStateException("Going nowhere!");
	        }

            System.out.println(output); // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
        }
    }

	protected static Map<Integer, Tile> setupTiles() {
		final HashMap<Integer, Tile> map = new HashMap<>();

		map.put(0, new Tile(0, new HashMap<Direction, Direction>()));

		HashMap<Direction, Direction> entriesAndExits = new HashMap<>();

		entriesAndExits.put(Direction.NORTH, Direction.SOUTH);
		entriesAndExits.put(Direction.EAST, Direction.SOUTH);
		entriesAndExits.put(Direction.WEST, Direction.SOUTH);
		map.put(1, new Tile(1, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.EAST, Direction.WEST);
		entriesAndExits.put(Direction.WEST, Direction.EAST);
		map.put(2, new Tile(2, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.SOUTH);
		map.put(3, new Tile(3, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.WEST);
		entriesAndExits.put(Direction.EAST, Direction.SOUTH);
		map.put(4, new Tile(4, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.EAST);
		entriesAndExits.put(Direction.WEST, Direction.SOUTH);
		map.put(5, new Tile(5, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.EAST, Direction.WEST);
		entriesAndExits.put(Direction.WEST, Direction.EAST);
		map.put(6, new Tile(6, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.SOUTH);
		entriesAndExits.put(Direction.EAST, Direction.SOUTH);
		map.put(7, new Tile(7, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.EAST, Direction.SOUTH);
		entriesAndExits.put(Direction.WEST, Direction.SOUTH);
		map.put(8, new Tile(8, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.SOUTH);
		entriesAndExits.put(Direction.WEST, Direction.SOUTH);
		map.put(9, new Tile(9, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.WEST);
		map.put(10, new Tile(10, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.NORTH, Direction.EAST);
		map.put(11, new Tile(11, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.EAST, Direction.SOUTH);
		map.put(12, new Tile(12, entriesAndExits));

		entriesAndExits = new HashMap<>();
		entriesAndExits.put(Direction.WEST, Direction.SOUTH);
		map.put(13, new Tile(13, entriesAndExits));

		return map;
	}

	static class Tile {
		int id;
		Map<Direction, Direction> entriesAndExits;

		public Tile(int id, Map<Direction, Direction> entriesAndExits) {
			this.id = id;
			this.entriesAndExits = entriesAndExits;
		}
	}

	enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
}