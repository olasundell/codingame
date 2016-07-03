import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
public class PowerOfThor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new PowerOfThor().solve(in));
	}

	public String solve(Scanner in) {
		List<String> result = new ArrayList<>();

		int LX = in.nextInt(); // the X position of the light of power
		int LY = in.nextInt(); // the Y position of the light of power
		int TX = in.nextInt(); // Thor's starting X position
		int TY = in.nextInt(); // Thor's starting Y position

		List<Point> bresenham = bresenham(new Point(TX, TY), new Point(LX, LY));
		for (int i = 0 ; i < bresenham.size() - 1 ; i++ ) {
			result.add(direction(bresenham.get(i), bresenham.get(i + 1)));
		}

		return result.stream().collect(Collectors.joining("\n"));
	}

	protected String direction(Point start, Point end) {
		double dX = start.getX() - end.getX();
		double dY = start.getY() - end.getY();

		StringBuilder builder = new StringBuilder();

		if (Math.signum(dY) < 0) {
			builder.append("S");
		} else if (Math.signum(dY) > 0) {
			builder.append("N");
		}

		if (Math.signum(dX) < 0) {
			builder.append("E");
		} else if (Math.signum(dX) > 0) {
			builder.append("W");
		}

		return builder.toString();
	}

	protected List<Point> bresenham(Point start, Point end) {
		List<Point> path = new ArrayList<>();
		double x0 = start.getX();
		double x1 = end.getX();

		double y0 = start.getY();
		double y1 = end.getY();

		double dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
		double dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
		double err = (dx>dy ? dx : -dy)/2;

		int x = (int) start.getX();
		int y = (int) start.getY();


		while (true) {
			path.add(new Point(x, y));

			if (x == x1 && y == y1) {
				break;
			}
			double e2 = err;
			if (e2 > -dx) {
				err -= dy;
				x += sx;
			}
			if (e2 < dy) {
				err += dx;
				y += sy;
			}
		}
		return path;
	}
}
