import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TelephoneNumberSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
	    Node head = new Node();

        for (int i = 0; i < N; i++) {
            String telephone = in.next();
	        put(head, telephone);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(countNoOfElememts(head)); // The number of elements (referencing a number) stored in the structure.
    }

	static void put(Node node, String number) {
		if (number.isEmpty()) {
			return;
		}

		final Integer key = Integer.valueOf(number.substring(0, 1));

		if (!node.children.containsKey(key)) {
			node.children.put(key, new Node());
		}

		put(node.children.get(key), number.substring(1));
	}

	static class Node {
		Map<Integer, Node> children = new HashMap<>();
	}

	protected static int countNoOfElememts(Node node) {
		return node.children.values()
				.stream()
				.mapToInt(n -> 1 + countNoOfElememts(n))
				.sum();
	}
}