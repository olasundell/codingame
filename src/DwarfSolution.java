import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class DwarfSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
	    System.out.println(solve(in));
    }

	private static Map<Integer, List<Integer>> map = new HashMap<>();

	public static String solve(Scanner in) {
		int n = in.nextInt(); // the number of relationships of influence
		for (int i = 0; i < n; i++) {
		    int x = in.nextInt(); // a relationship of influence between two people (x influences y)
		    int y = in.nextInt();

			if (!map.containsKey(x)) {
				map.put(x, new ArrayList<>());
			}

			map.get(x).add(y);
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		int max = 0;

		final Iterator<Integer> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			int h = maxHeight(iterator.next());

			if (h > max) {
				max = h;
			}
		}

		return String.valueOf(max); // The number of people involved in the longest succession of influences
	}

	protected static int maxHeight(int key) {
		final List<Integer> integers = map.get(key);
		if (integers == null || integers.isEmpty()) {
			return 1;
		}

		int max = 0;

		for (int p: integers) {
			int h = maxHeight(p) + 1;
			if (h > max) {
				max = h;
			}
		}

		return max;
	}

	protected static int maxHeight(Tree p) {
		if (p.isEmpty()) return 0;
		int max = 0;
		for (Tree child: p.children) {
			int h = maxHeight(child);
			if (h > max) {
				max = h;
			}
		}
		return max;
	}

	public static class Tree {
		List<Tree> children;
		int id;

		public boolean isEmpty() {
			return children.isEmpty();
		}
	}
}