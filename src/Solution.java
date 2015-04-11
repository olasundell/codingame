import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) throws FileNotFoundException {
//        Scanner in = new Scanner(System.in);
	    Scanner in = new Scanner(new File("/Users/olasundell/code/codingame/rollercoaster-large-dataset.txt"));
        int places = in.nextInt();
        int noOfIterations = in.nextInt();
        int noOfGroups = in.nextInt();

	    Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < noOfGroups; i++) {
            int Pi = in.nextInt();
	        queue.add(Pi);
        }

	    long totalAmountOfPassengers = 0;
	    Deque<Integer> riders = new ArrayDeque<>();

	    for (int i = 0 ; i < noOfIterations ; i++) {
		    int currentPlaces = places;

		    while (!queue.isEmpty()) {

			    if (queue.peekFirst() > currentPlaces) {
				    break;
			    }

			    int groupSize = queue.removeFirst();
			    riders.addFirst(groupSize);
			    currentPlaces -= groupSize;
			    totalAmountOfPassengers += groupSize;
		    }

		    queue.addAll(riders);
		    riders.clear();
	    }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(totalAmountOfPassengers);
    }
}