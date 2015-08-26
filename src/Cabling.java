import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cabling {
	private final static Point ORIGIN = new Point(0,0);

	public static String solve(Scanner in) {
		int N = in.nextInt();
		boolean board[][] = new boolean[N][N];
		List<Point> houses = new ArrayList<>();
		Point closestToOrigin = null;

		for (int i = 0; i < N; i++) {
			int X = in.nextInt();
			int Y = in.nextInt();

			final Point house = new Point(X, Y);
			if (closestToOrigin == null) {
				closestToOrigin = house;
			} else {
				if (distance(house, ORIGIN) < distance(closestToOrigin, ORIGIN)) {
					closestToOrigin = house;
				}
			}
			houses.add(house);
			board[Y][X] = true;
		}



		return "";
	}

	protected static int distance(Point one, Point two) {
		return (int) (Math.abs(one.getX() - two.getX()) + Math.abs(one.getY() - two.getY()));
	}
}
