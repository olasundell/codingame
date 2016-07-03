import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class StockExchangeSolution {

    public static void main(String args[]) {
	    Scanner in = new Scanner(System.in);
	    System.out.println(solve(in));
    }

	protected static String solve(Scanner in) {
        int n = in.nextInt();
        in.nextLine();
        String vs = in.nextLine();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

	    List<Integer> list = Arrays.stream(vs.split(" ")).map(Integer::valueOf).collect(Collectors.toList());

	    int diff = Integer.MAX_VALUE;

		for (int i = 0 ; i < list.size() - 1; i++) {
			if (list.get(i) <= list.get(i + 1)) {
				continue;
			}

			for (int j = i + 1 ; j < list.size() ; j++) {
				final int current = list.get(j) - list.get(i);
				if (current < diff) {
					diff = current;
				} else if (list.get(j) > list.get(i)){
					break;
				}
			}
		}

		if (diff < 0) {
			return String.valueOf(diff);
		} else {
			return "0";
		}
    }
}