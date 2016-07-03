import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TheGiftSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();
	    List<Integer> budgets = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            budgets.add(in.nextInt());
        }

	    if (budgets.stream().mapToInt(value -> value).sum() < C) {
		    System.out.println("IMPOSSIBLE");
		    return;
	    }

	    Collections.sort(budgets);
	    List<Integer> result = iterate(budgets, C);

	    final int sum = result.stream().mapToInt(value -> value).sum();
	    if (sum < C) {
		    // scream
		    System.out.println("Argh, sum is " + sum + " and cost is "+ C);

		    return;
	    }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

	    result.stream().forEach(System.out::println);
    }

	private static List<Integer> iterate(List<Integer> budgets, int cost) {
		if (budgets.isEmpty()) {
			return new ArrayList<>();
		}

		if (budgets.get(0) <= cost / budgets.size()) {
			final List<Integer> list = iterate(budgets.subList(1, budgets.size()), cost - budgets.get(0));
			list.add(0, budgets.get(0));

			return list;
		}

		System.err.println("cost is " + cost + " and budgets size " + budgets.size() + " with first participant " + budgets.get(0));

		int partialCost = cost / budgets.size();
		int remainder = cost % budgets.size();

		System.err.println("partial cost is "+ partialCost +", remainder is " + remainder);
		List<Integer> list = new ArrayList<>();

		for (int i = 0 ; i < budgets.size() - remainder; i++ ) {
			list.add(partialCost);
		}

		for (int i = budgets.size() - remainder; i < budgets.size() ; i++) {
			list.add(partialCost + 1);
		}

		return list;
	}
}