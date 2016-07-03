import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Defib solution
 */
public class DefibSolution {

	static class MyComparator implements Comparator<DefibPoint> {
		final DefibPoint origin;

		public MyComparator(DefibPoint origin) {
			this.origin = origin;
		}

		@Override
		public int compare(DefibPoint o1, DefibPoint o2) {

			return (int) Math.round(distance(origin, o1) - distance(origin, o2));
		}

		private double distance(DefibPoint o1, DefibPoint o2) {
			double x = (o2.lon - o1.lon) * Math.cos((o1.lat + o2.lat) / 2);
			double y = (o2.lat - o1.lat);
			return Math.sqrt(x*x + y*y) * 6371;
		}
	};

	/**
	 * Auto-generated code below aims at helping you parse
	 * the standard input according to the problem statement.
	 **/

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		double LON = Double.valueOf(in.next().replace(',', '.'));
		in.nextLine();
		double LAT = Double.valueOf(in.next().replace(',', '.'));
		in.nextLine();
		int N = in.nextInt();
		in.nextLine();

		DefibPoint origin = new DefibPoint();
		origin.lon = LON;
		origin.lat = LAT;

		SortedMap<DefibPoint, Defib> defibMap = new TreeMap<>(new MyComparator(origin));

		for (int i = 0; i < N; i++) {
			String DEFIB = in.nextLine();
			String[] str = DEFIB.split(";");
			Defib defib = new Defib();

			defib.name = str[1];
			defib.address = str[2];
			defib.point = new DefibPoint();

			defib.point.lon = Double.valueOf(str[4].replace(',','.'));
			defib.point.lat = Double.valueOf(str[5].replace(',','.'));

			System.err.println("Putting " + defib.toString());

			defibMap.put(defib.point, defib);
		}


		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(defibMap.get(defibMap.firstKey()).name);
	}

	static class DefibPoint {
		double lat;
		double lon;

		@Override
		public String toString() {
			return "DefibPoint{" +
					"lat=" + lat +
					", lon=" + lon +
					'}';
		}
	}

	static class Defib {
		String name;
		String address;
		DefibPoint point;

		@Override
		public String toString() {
			return "Defib{" +
					"name='" + name + '\'' +
					", address='" + address + '\'' +
					", point=" + point +
					'}';
		}
	}
}
