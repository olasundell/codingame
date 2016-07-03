import java.awt.*;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ThorPlayer {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int LX = in.nextInt(); // the X position of the light of power
		int LY = in.nextInt(); // the Y position of the light of power
		int TX = in.nextInt(); // Thor's starting X position
		int TY = in.nextInt(); // Thor's starting Y position

		Transform transform = new Transform(TX, TY, LX, LY);
		Point goal = transform.getGoal();
		Point thor = transform.getThor();

		int deltaX = goal.x - thor.x;
		int deltaY = goal.y - thor.y;
		int err = 2 * deltaY - deltaX;
		int y = thor.y;

		for (int x = thor.x + 1; x <= goal.x; x += Math.signum(deltaX)) {
			if (err > 0) {
				y++;

			}
		}
	}

	private static int findOctant(Point goal, Point current) {
		return 0;
	}

	private static String eastOrWest(int deltaX) {
		return Math.signum(deltaX) > 0 ? "W" : "E";
	}

	private static String southOrNorth(int deltaY) {
		return Math.signum(deltaY) > 0 ? "S" : "N";
	}

	static class Transform {
		private final Point thor;
		private final Point goal;

		public Transform(int thorX, int thorY, int goalX, int goalY) {
//			int octant = findOctant(thorX, thorY, goalX, goalY);

			thor = null;
			goal = null;
//			thor = switchOctant(octant, thorX, thorY);
//			goal = switchOctant(octant, goalX, goalY);
		}

		protected int findOctant(int x, int y, int x1, int y1) {
			int deltaX = x - x1;
			int deltaY = y - y1;

			int octant = 0;

			if (deltaY < 0) {
				octant += 4;
			}

			return octant;
		}

		protected Point switchOctant(int octant, int origX, int origY) {
			switch (octant) {
			case 0:
				return new Point(origX, origY);
			case 1:
				return new Point(origY, origX);
			case 2:
				return new Point(-origY, origX);
			case 3:
				return new Point(-origX, origY);
			case 4:
				return new Point(-origX, -origY);
			case 5:
				return new Point(-origY, -origX);
			case 6:
				return new Point(origY, -origX);
			case 7:
				return new Point(origX, -origY);
			}

			return new Point(origX, origY);
		}

		public Point getGoal() {
			return goal;
		}

		public Point getThor() {
			return thor;
		}
	}
}

/*
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
/*
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		double LON = Double.valueOf(in.next().replace(',','.'));
		in.nextLine();
		double LAT = Double.valueOf(in.next().replace(',','.'));
		in.nextLine();
		int N = in.nextInt();
		in.nextLine();

		SortedMap<DefibPoint, Defib> = new TreeMap<>(new )

		List<Defib> defibs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String DEFIB = in.nextLine();
			String[] str = DEFIB.split(";");
			Defib defib = new Defib();

			defib.name = str[1];
			defib.address = str[2];

			defib.lon = Double.valueOf(str[4].replace(',','.'));
			defib.lat = Double.valueOf(str[5].replace(',','.');

			defibs.add(defib);
		}


		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println("answer");
	}

	static class Defib {
		String name;
		String address;
		double lat;
		double lon;
	}
}
 */