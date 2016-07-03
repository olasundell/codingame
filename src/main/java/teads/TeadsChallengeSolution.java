package teads;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class TeadsChallengeSolution {
	Map<Integer, Set<Integer>> relations = new HashMap<>();
	Map<Integer, Set<Integer>> reverseRelations = new HashMap<>();
	Map<Integer, Integer> weights = new HashMap<>();
	Map<Integer, Integer> reverseWeights = new HashMap<>();

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println(new TeadsChallengeSolution().solve(in));
	}

	public String solve(Scanner in) {
		int n = in.nextInt(); // the number of adjacency relations
		for (int i = 0; i < n; i++) {
			int xi = in.nextInt(); // the ID of a person which is adjacent to yi
			int yi = in.nextInt(); // the ID of a person which is adjacent to xi

			if (!relations.containsKey(xi)) {
				relations.put(xi, new HashSet<>());
			}

			relations.get(xi).add(yi);

			if (!reverseRelations.containsKey(yi)) {
				reverseRelations.put(yi, new HashSet<>());
			}

			reverseRelations.get(yi).add(xi);
		}

		int shortestPath = Integer.MAX_VALUE;

		final HashSet<Integer> visited = new HashSet<>();
		for (int p : relations.keySet()) {
			visited.clear();
			final int d = distanceToEdge(p, visited);

			if (d < shortestPath) {
				shortestPath = d;
			}
		}

		return String.valueOf(shortestPath);
	}

	int distanceToEdge(int p, Set<Integer> visited) {
		if (visited.contains(p)) {
			return 1;
		}

		visited.add(p);

		if (!relations.containsKey(p) || relations.get(p).isEmpty()) {
			return 0;
		}

		int maxDepth = 0;
		int d;

		for (int n : relations.get(p)) {
			if (visited.contains(n)) {
				continue;
			}

			if (!weights.containsKey(n)) {
				d = distanceToEdge(n, visited) + 1;
				weights.put(n, d);
			} else {
				visited.add(n);
				d = weights.get(n);
			}
			if (d > maxDepth) {
				maxDepth = d;
			}
		}

		if (reverseRelations.containsKey(p)) {
			Set<Integer> reverse = reverseRelations.get(p);

			for (int r : reverse) {
				if (!visited.contains(r)) {
					if (reverseWeights.containsKey(r)) {
						d = reverseWeights.get(r);
					} else {
						d = distanceToEdge(r, visited) + 1;
						reverseWeights.put(r, d);
					}
					if (d > maxDepth) {
						maxDepth = d;
					}
				}
			}
		}

		return maxDepth;
	}
}
