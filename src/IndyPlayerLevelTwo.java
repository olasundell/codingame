import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) throws CloneNotSupportedException {
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
		        board[i][j] = tiles.get(key).clone();
		        System.err.print(key + " ");
	        }
	        System.err.println();
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        in.nextLine();

	    boolean first = true;
	    boolean cameFromAnExit = false;

        // game loop
        while (true) {
	        int noOfRocks = 0;

	        if (!first) {
		        noOfRocks = in.nextInt();
	        } else {
		        first = false;
	        }
            int XI = in.nextInt();
            int YI = in.nextInt();

	        String POS = in.next();
	        System.err.println("Read " + XI + " " + YI + ", no of rocks: " + noOfRocks + ", pos: " + POS);

	        for (int i = 0 ; i < noOfRocks ; i++) {
		        // TODO read rocks.
		        in.nextLine();
	        }

	        Direction moveDirection = Direction.NONE;
	        Direction cameFromDirection;

	        final Tile tile = board[YI][XI];
	        System.err.print("Current tile is type " + tile.id + ", ");
	        switch (POS) {
	        case "TOP":
		        cameFromDirection = Direction.NORTH;
		        break;
	        case "LEFT":
		        cameFromDirection = Direction.WEST;
		        break;
	        case "RIGHT":
		        cameFromDirection = Direction.EAST;
		        break;
	        default:
		        System.err.println("came from nowhere!");
		        throw new IllegalStateException("Came from nowhere!");
	        }

	        System.err.println("came from the " + cameFromDirection.name());
	        if (cameFromAnExit) {
		        if (tile.entriesAndExits.values().contains(Direction.SOUTH)) {
			        moveDirection = Direction.SOUTH;
		        } else {
			        for (Map.Entry<Direction, Direction> entry : tile.entriesAndExits.entrySet()) {
				        if (entry.getValue() == cameFromDirection) {
					        moveDirection = entry.getKey();
					        break;
				        }
			        }
		        }
	        } else {
		        moveDirection = tile.entriesAndExits.get(cameFromDirection);

	        }

	        if (moveDirection == Direction.NONE) {
		        // TODO scream
	        }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

	        String output;

	        System.err.println("Moving to the " + moveDirection.name());

	        int newX = 0,
				newY = 0;

	        switch (moveDirection) {
	        case SOUTH:
//		        output = XI + " " + (YI + 1);
		        newX = XI;
		        newY = YI + 1;
				break;
	        case EAST:
//		        output = (XI + 1) + " " + YI;
		        newX = XI + 1;
		        newY = YI;
		        break;
	        case WEST:
//		        output = (XI - 1) + " " + YI;
		        newX = XI - 1;
		        newY = YI;
		        break;
	        default:
		        System.err.println("Going nowhere!");
		        throw new IllegalStateException("Going nowhere!");
	        }

	        final Tile destinationTile = board[newY][newX];

	        System.err.println("Destination tile is of type " + destinationTile.id + " with entries and exits: " +
			        destinationTile.entriesAndExits.entrySet().stream()
					        .map((e) -> e.getKey().name() + ": " + e.getValue().name())
					        .collect(Collectors.joining(", ")));

	        Rotation rotation = Rotation.NONE;

	        if (!destinationTile.hasEntry(moveDirection.opposite())) {
		        if (destinationTile.hasEntry(moveDirection.rotate(Rotation.LEFT))) {
			        rotation = Rotation.LEFT;
			        cameFromAnExit = false;
		        } else if (destinationTile.hasEntry(moveDirection.rotate(Rotation.RIGHT))) {
			        rotation = Rotation.RIGHT;
			        cameFromAnExit = false;
		        } else if (destinationTile.hasExit(moveDirection.rotate(Rotation.LEFT))) {
			        cameFromAnExit = true;
			        rotation = Rotation.LEFT;
		        } else if (destinationTile.hasExit(moveDirection.rotate(Rotation.RIGHT))) {
			        cameFromAnExit = true;
			        rotation = Rotation.RIGHT;
		        } else {
			        throw new IllegalStateException("Could not find a way out!");
		        }
		        System.err.println("Rotating tile to the " + rotation.name());
		        output = newX + " " + newY + " " + rotation.name();
		        board[newY][newX].rotate(rotation);
	        } else {
		        output = "WAIT";
	        }

            System.out.println(output); // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
        }
    }

	protected static Map<Integer, Tile> setupTiles() {
		final HashMap<Integer, Tile> map = new HashMap<>();

		map.put(0, new Tile(0, new HashMap<>()));

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

		for (int i = 1 ; i <= 13 ; i++) {
			Tile t = map.get(i);
			map.put(-i, new Tile(-i, t.entriesAndExits, false));
		}

		return map;
	}

	static class Tile implements Cloneable {
		int id;
		boolean rotateable;
		Map<Direction, Direction> entriesAndExits;

		public Tile(int id, Map<Direction, Direction> entriesAndExits) {
			this(id, entriesAndExits, true);
		}

		public Tile(int id, Map<Direction, Direction> entriesAndExits, boolean rotateable) {
			this.id = id;
			this.entriesAndExits = entriesAndExits;
			this.rotateable = rotateable;
		}

		public boolean hasEntry(Direction direction) {
			return entriesAndExits.keySet().contains(direction);
		}

		public boolean hasExit(Direction direction) {
			return entriesAndExits.values().contains(direction);
		}

		public void rotate(Rotation rotation) {
			Map<Direction, Direction> newEntriesAndExits = new HashMap<>();

			for (Map.Entry<Direction, Direction> entry: entriesAndExits.entrySet()) {
				newEntriesAndExits.put(entry.getKey().rotate(rotation), entry.getValue().rotate(rotation));
			}

			this.entriesAndExits = newEntriesAndExits;
		}

		public Tile clone() throws CloneNotSupportedException {
			super.clone();
			return new Tile(this.id, new HashMap<>(this.entriesAndExits), this.rotateable);
		}
	}

	enum Rotation {
		NONE, LEFT, RIGHT;
	}

	enum Direction {
		NONE, NORTH, SOUTH, EAST, WEST;

		Direction opposite() {
			switch (this) {
			case NORTH:
				return SOUTH;
			case SOUTH:
				return NORTH;
			case WEST:
				return EAST;
			case EAST:
				return WEST;
			}
			return null;
		}

		Direction rotate(Rotation rotation) {
			if (rotation == Rotation.LEFT) {
				switch (this) {
				case NORTH:
					return WEST;
				case SOUTH:
					return EAST;
				case WEST:
					return SOUTH;
				case EAST:
					return NORTH;
				}
			} else {
				switch (this) {
				case NORTH:
					return EAST;
				case SOUTH:
					return WEST;
				case WEST:
					return NORTH;
				case EAST:
					return SOUTH;
				}
			}
			return null;
		}
	}
}