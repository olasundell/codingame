import java.awt.*;
import java.util.Scanner;

public class ShadowsOfTheKnight {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new ShadowsOfTheKnight().solve(in);
	}

	public void solve(Scanner in) {
		int W = in.nextInt(); // width of the building.
		int H = in.nextInt(); // height of the building.
		int N = in.nextInt(); // maximum number of turns before game over.
		int X0 = in.nextInt();
		int Y0 = in.nextInt();

		Board board = new Board(W, H);
		Point currentPos = new Point(X0, Y0);

		while (in.hasNext()) {
			String bombDir = in.next();
			board.crop(bombDir, currentPos);

			currentPos = board.middle();
			System.out.println(currentPos.x + " " + currentPos.y);
		}
	}

	public static class Board {
		Point upLeft;
		Point downRight;

		public Board(int xSize, int ySize) {
			upLeft = new Point(0, 0);
			downRight = new Point(xSize - 1, ySize - 1);
		}

		public void crop(String bombDir, Point currentPos) {

			switch (bombDir) {
				case "U":
					upLeft.setLocation(currentPos.x, upLeft.y);
					downRight.setLocation(downRight.x, currentPos.y - 1);
					break;
				case "UR":
					upLeft.setLocation(currentPos.x + 1, upLeft.y);
					downRight.setLocation(downRight.x, currentPos.y - 1);
					break;
				case "R":
					upLeft.setLocation(currentPos.x + 1, currentPos.y);
					downRight.setLocation(downRight.x, currentPos.y);
					break;
				case "DR":
					upLeft.setLocation(currentPos.x + 1, currentPos.y + 1);
					downRight.setLocation(downRight.x, downRight.y);
					break;
				case "D":
					upLeft.setLocation(currentPos.x, currentPos.y + 1);
					downRight.setLocation(currentPos.x, downRight.y);
					break;
				case "DL":
					upLeft.setLocation(upLeft.x, currentPos.y + 1);
					downRight.setLocation(currentPos.x - 1, downRight.y);
					break;
				case "L":
					upLeft.setLocation(upLeft.x, currentPos.y);
					downRight.setLocation(currentPos.x, currentPos.y);
					break;
				case "UL":
					upLeft.setLocation(upLeft.x, upLeft.y);
					downRight.setLocation(currentPos.x - 1, currentPos.y - 1);
					break;
			}
			System.err.println(upLeft.toString() +" - " + downRight.toString());
		}

		public Point middle() {
			int x = (int) (Math.abs(upLeft.getX() - downRight.getX()) / 2 + upLeft.getX());
			int y = (int) (Math.abs(upLeft.getY() - downRight.getY()) / 2 + upLeft.getY());

			return new Point(x, y);
		}
	}
}
