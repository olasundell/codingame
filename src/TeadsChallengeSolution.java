import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TeadsChallengeSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
	    Map<Integer, Node> nodeMap = new HashMap<>();
        int n = in.nextInt(); // the number of adjacency relations
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi

	        putParentAndChild(nodeMap, xi, yi);
        }

	    // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(findLeastDistance(nodeMap)); // The minimal amount of steps required to completely propagate the advertisement
    }

	private static int findLeastDistance(Map<Integer, Node> nodeMap) {
		int leastDistance = Integer.MAX_VALUE;

		for (Node node: nodeMap.values()) {
			final int distance = largestDistance(node);
			if (distance < leastDistance) {
				System.err.println("least distance is " + leastDistance + ", distance is "+ distance + ", for id " + node.id);
				leastDistance = distance;
			}
		}
		return leastDistance;
	}

	private static void putParentAndChild(Map<Integer, Node> nodeMap, int xi, int yi) {
		if (!nodeMap.containsKey(xi)) {
			final Node node = new Node(Node.NO_PARENT);
			node.id = xi;
			nodeMap.put(xi, node);
		}

		final Node child;
		final Node parent = nodeMap.get(xi);

		if (!nodeMap.containsKey(yi)) {
			child = new Node(parent);
			child.id = yi;
			nodeMap.put(yi, child);
		} else {
			child = nodeMap.get(yi);
			if (child.parent == null) {
				child.parent = parent;
			}
		}

		parent.children.add(child);
		System.err.println("Putting " + xi + " as a father to " + yi);
	}

	public static class Node {
		public static Node NO_PARENT = null;

		int id;

		List<Node> children = new ArrayList<>();
		Node parent;

		public Node(Node parent) {
			this.parent = parent;
		}
	}

	public static int largestDistance(Node node) {
		return largestDistance(node, new HashSet<>()) - 1;
	}

	private static int largestDistance(Node node, Set<Node> visited) {
		int currentLargest = 0;
		visited.add(node);

		for (Node child: node.children) {
			if (visited.contains(child)) {
				continue;
			}
			int d = largestDistance(child, visited);
			if (d > currentLargest) {
				currentLargest = d;
			}
		}

		if (node.parent != null && !visited.contains(node.parent)) {
			int d = largestDistance(node.parent, visited);
			if (d > currentLargest) {
				currentLargest = d;
			}
		}

		return currentLargest + 1;
	}
}