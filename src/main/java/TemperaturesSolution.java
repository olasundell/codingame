import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TemperaturesSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of temperatures to analyse

	    if (N == 0) {
		    System.out.println(0);
		    return;
	    }

        in.nextLine();
        String TEMPS = in.nextLine(); // the N temperatures expressed as integers ranging from -273 to 5526
	    SortedSet<Integer> temps = new TreeSet<>(new Comparator<Integer>() {
		    @Override
		    public int compare(Integer o1, Integer o2) {
			    if (Math.abs(o1) == Math.abs(o2) && !o1.equals(o2)) {
				    if (o1 > o2) {
					    return -1;
				    } else {
					    return 1;
				    }
			    }

			    return Math.abs(o1) - Math.abs(o2);
		    }
	    });

	    temps.addAll(Arrays.stream(TEMPS.split(" ")).map(Integer::valueOf).collect(Collectors.toList()));

	    System.err.println("No of temperatures: " + temps.size());

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(temps.first());
    }
}