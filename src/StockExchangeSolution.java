import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class StockExchangeSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String vs = in.nextLine();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

	    List<Integer> list = Arrays.stream(vs.split(" ")).map(Integer::valueOf).collect(Collectors.toList());

	    int indexForLeast = 0;
	    int least = Integer.MAX_VALUE;

	    for (int i = 0 ; i < list.size() ; i++) {
		    if (list.get(i) < least) {
			    least = list.get(i);
			    indexForLeast = i;
		    }
	    }

	    Optional<Integer> highestOpt = list.subList(0, indexForLeast).stream().max(Comparator.<Integer>naturalOrder());

	    int highest = least;

	    if (highestOpt.isPresent()) {
		    highest = highestOpt.get();
	    }

        System.out.println(least - highest);
    }
}