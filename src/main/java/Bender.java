import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 Bender starts from the place indicated by the @ symbol on the map and heads SOUTH.
 Bender finishes his journey and dies when he reaches the suicide booth marked $.
 Obstacles that Bender may encounter are represented by # or X.
 When Bender encounters an obstacle, he changes direction using the following priorities: SOUTH, EAST, NORTH and WEST. So he first tries to go SOUTH, if he cannot, then he will go EAST, if he still cannot, then he will go NORTH, and finally if he still cannot, then he will go WEST.
 Along the way, Bender may come across path modifiers that will instantaneously make him change direction. The S modifier will make him turn SOUTH from then on, E, to the EAST, N to the NORTH and W to the WEST.
 The circuit inverters (I on map) produce a magnetic field which will reverse the direction priorities that Bender should choose when encountering an obstacle. Priorities will become WEST, NORTH, EAST, SOUTH. If Bender returns to an inverter I, then priorities are reset to their original state (SOUTH, EAST, NORTH, WEST).
 Bender can also find a few beers along his path (B on the map) that will give him strength and put him in “Breaker” mode. Breaker mode allows Bender to destroy and automatically pass through the obstacles represented by the character X (only the obstacles X). When an obstacle is destroyed, it remains so permanently and Bender maintains his course of direction. If Bender is in Breaker mode and passes over a beer again, then he immediately goes out of Breaker mode. The beers remain in place after Bender has passed.
 2 teleporters T may be present in the city. If Bender passes over a teleporter, then he is automatically teleported to the position of the other teleporter and he retains his direction and Breaker mode properties.
 Finally, the space characters are blank areas on the map (no special behavior other than those specified above).
 */
 
 class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new Bender().solve(in));
	}
 }
 
class Bender {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new Bender().solve(in));
	}

	public String solve(Scanner in) {
		List<String> result = new ArrayList<>();
		in.nextLine();

		State state = readMap(in);

		while (state.isAlive()) {
			Move currentMove = createMove(state);

			if (state.moves.contains(currentMove)) {
				System.err.println("--LOOP--");
				return "LOOP";
			} else {
				state.moves.add(currentMove);
			}

			String s = executeMove(currentMove, state);
			System.err.println(currentMove.toString());

			result.add(s);
		}

		return result.stream().collect(Collectors.joining("\n"));
	}

	protected Move createMove(State state) {
		Point newPos = new Point();

		switch (state.getCurrentTile().type) {
			case NORTH:
				return new Move(state.currentPos, calcNewPosition(Direction.NORTH, state.currentPos), Direction.NORTH);
			case SOUTH:
				return new Move(state.currentPos, calcNewPosition(Direction.SOUTH, state.currentPos), Direction.SOUTH);
			case EAST:
				return new Move(state.currentPos, calcNewPosition(Direction.EAST, state.currentPos), Direction.EAST);
			case WEST:
				return new Move(state.currentPos, calcNewPosition(Direction.WEST, state.currentPos), Direction.WEST);
		}

		// first, try to go where we're headed at the moment.
		Move candidate = checkMoveLegality(state, state.currentDirection);
		if (candidate != null) {
			return candidate;
		}

		for (Direction d: state.priorities) {
			candidate = checkMoveLegality(state, d);
			if (candidate != null) return candidate;
		}

		// TODO we could throw an exception here, just for good measure
		return null;
	}

	private Move checkMoveLegality(State state, Direction d) {
		Point candidate = calcNewPosition(d, state.currentPos);
		switch (state.getTile(candidate).type) {
		case BEER:
		case START:
		case BLANK:
		case NORTH:
		case SOUTH:
		case EAST:
		case WEST:
		case INVERTER:
		case SUICIDE:
		case TELEPORTER:
			return new Move(state.currentPos, candidate, d);
		case OBSTACLE:
			if (state.beer) {
				return new Move(state.currentPos, candidate, d);
			}
		case WALL:
		}
		return null;
	}

	public static Point calcNewPosition(Direction direction, Point oldPos) {
		Point newPos = new Point();
		switch (direction) {
		case NORTH:
			newPos.setLocation(oldPos.x, oldPos.y - 1);
			break;
		case SOUTH:
			newPos.setLocation(oldPos.x, oldPos.y + 1);
			break;
		case EAST:
			newPos.setLocation(oldPos.x + 1, oldPos.y);
			break;
		case WEST:
			newPos.setLocation(oldPos.x - 1, oldPos.y);
			break;
		}

		return newPos;
	}

	protected State readMap(Scanner in) {
		List<List<Tile>> result = new ArrayList<>();
		Map<Character, Type> types = new HashMap<>();
		Point startingPos = new Point(0, 0);

		for (Type t: Type.values()) {
			types.put(t.rep, t);
		}

		while (in.hasNextLine()) {
			ArrayList<Tile> line = new ArrayList<>();
			result.add(line);

			for (char c: in.nextLine().toCharArray()) {
				Tile tile = new Tile(types.get(c));
				line.add(tile);

				if (tile.type == Type.START) {
					startingPos = new Point(line.size() -1, result.size() - 1);
				}
			}
		}

		return new State(result, startingPos);
	}

	String executeMove(Move move, State state) {
		state.currentPos = move.newPos;
		state.currentDirection = move.direction;

		switch (state.getCurrentTile().type) {
		case BEER:
			state.beer = !state.beer;
			if (state.beer) {
				System.err.println("BEER!");
				state.moves.clear();
			} else {
				System.err.println("Beer no more");
				state.moves.clear();
			}
			break;
		case OBSTACLE:
			if (state.beer) {
				state.setTile(move.newPos, Type.BLANK);
				System.err.println("Broke down obstacle");
				state.moves.clear();
			}
			break;
		case INVERTER:
			state.flipPriorities();
			break;
		case SUICIDE:
			state.hasCommittedSuicide = true;
			break;
		case TELEPORTER:
			state.currentPos = state.teleporters.get(state.currentPos);
			break;
		}

		return move.direction.name();
	}

	static class Move {
		final Point oldPos;
		final Point newPos;
		final Direction direction;

		Move(Point oldPos, Point newPos, Direction direction) {
			this.oldPos = oldPos;
			this.newPos = newPos;
			this.direction = direction;
		}

		@Override
		public boolean equals(Object obj) {
			return (obj instanceof Move) &&
					this.newPos.equals(((Move)obj).newPos) &&
					this.oldPos.equals(((Move)obj).oldPos);
		}

		@Override
		public int hashCode() {
			int result = oldPos.hashCode();
			result = 31 * result + newPos.hashCode();
			result = 31 * result + direction.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return "direction=" + direction +
					       ", " + oldPos.x + "," + oldPos.y +
					       " to " + newPos.x + "," + newPos.y;
		}
	}

	static class State {
		Direction currentDirection = Direction.SOUTH;
		List<List<Tile>> map;
		Map<Point, Point> teleporters = new HashMap<>();
		Point currentPos;
		boolean beer = false;
		boolean hasCommittedSuicide = false;
		List<Direction> priorities = new ArrayList<>();
		Set<Move> moves = new HashSet<>();

		State(List<List<Tile>> map, Point currentPos) {
			this.currentPos = currentPos;
			this.map = map;

			findTeleporters(map);

			priorities.add(Direction.SOUTH);
			priorities.add(Direction.EAST);
			priorities.add(Direction.NORTH);
			priorities.add(Direction.WEST);
		}

		private void findTeleporters(List<List<Tile>> map) {
			List<Point> list = new ArrayList<>();
			for (int i = 0 ; i < map.size() ; i++) {
				for (int j = 0 ; j < map.get(i).size() ; j++) {
					if (map.get(i).get(j).type == Type.TELEPORTER) {
						list.add(new Point(j, i));
					}
				}
			}

			// TODO this assumes a whole lot
			if (!list.isEmpty()) {
				teleporters.put(list.get(0), list.get(1));
				teleporters.put(list.get(1), list.get(0));
			}
		}

		boolean isAlive() {
			return !hasCommittedSuicide;
		}

		void flipPriorities() {
			Collections.reverse(priorities);
		}

		@Override
		public String toString() {
			return map.stream()
					.map(l -> l.stream()
							.map(Tile::toString)
							.collect(Collectors.joining()))
					.collect(Collectors.joining("\n"));
		}

		Tile getTile(Point candidate) {
			return map.get(candidate.y).get(candidate.x);
		}

		Tile getCurrentTile() {
			return getTile(currentPos);
		}

		void setTile(Point newPos, Type type) {
			map.get(newPos.y).set(newPos.x, new Tile(type));
		}
	}

	static class Tile {
		final Type type;

		Tile(Type type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return String.valueOf(type.rep);
		}
	}

	enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}

	public enum Type {
//		#, X, @, $, S, E, N, W, B, I, T
		WALL('#'),
		OBSTACLE('X'),
		START('@'),
		SUICIDE('$'),
		BEER('B'),
		NORTH('N'),
		SOUTH('S'),
		WEST('W'),
		EAST('E'),
		TELEPORTER('T'),
		INVERTER('I'),
		BLANK(' ');


		char rep;

		Type(char rep) {
			this.rep = rep;
		}
	}
}
