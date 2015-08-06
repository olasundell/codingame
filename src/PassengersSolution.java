
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 *
 */
class PassengersSolution {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new File("/Users/olasundell/NetBeansProjects/codingame/rollercoaster-large-dataset.txt"));
        int places = in.nextInt();
        int noOfIterations = in.nextInt();
        int noOfGroups = in.nextInt();

        System.err.println("Starting");
        List<Integer> queue = new ArrayList<>();
        long totalGroupSize = 0;

        for (int i = 0; i < noOfGroups; i++) {
            int Pi = in.nextInt();
            queue.add(Pi);
            totalGroupSize += Pi;
        }

	    long totalAmountOfPassengers = getTotalAmountOfPassengers(places, noOfIterations, queue, totalGroupSize);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(totalAmountOfPassengers);
    }

	private static long getTotalAmountOfPassengers(int places, int noOfIterations, List<Integer> queue, long totalGroupSize) {
//		return getTotalAmountOfPassengersOneLoop(places, noOfIterations, queue, totalGroupSize);
//		return getTotalAmountOfPassengersTwoLoops(places, noOfIterations, queue, totalGroupSize);
		return getTotalAmountOfPassengersTwoLoopsArray(places, noOfIterations, queue.toArray(new Integer[queue.size()]), totalGroupSize);

	}
	private static long getTotalAmountOfPassengersOneLoop(int places, int noOfIterations, List<Integer> queue, long totalGroupSize) {
		long totalAmountOfPassengers = 0;
		int idx = 0;
		int oldIdx = -1;
		int currentPassengers = 0;
		int iterations = 0;

		while (totalGroupSize > currentPassengers) {
			if (queue.get(idx) > places - currentPassengers || oldIdx == idx) {
				currentPassengers = 0;
				oldIdx = idx;
				if (++iterations > noOfIterations) {
					break;
				}
			}

			currentPassengers += queue.get(idx);

			idx = (idx + 1) % queue.size();

			totalAmountOfPassengers += currentPassengers;
		}

		return totalAmountOfPassengers;
	}

	private static long getTotalAmountOfPassengersTwoLoops(int places, int noOfIterations, List<Integer> queue, long totalGroupSize) {
		long totalAmountOfPassengers = 0;
		int idx = 0;
		int oldIdx;
		int currentPassengers = 0;

		for (int i = 0; i < noOfIterations; i++) {
		    currentPassengers = 0;
		    oldIdx = idx;
		    while (totalGroupSize > currentPassengers) {
		        if (queue.get(idx) > places - currentPassengers) {
		            break;
		        }

		        currentPassengers += queue.get(idx);

		        idx = (idx + 1) % queue.size();
		        if (idx == oldIdx) {
		            break;
		        }

		    }
		    totalAmountOfPassengers += currentPassengers;
		}
		return totalAmountOfPassengers;
	}

	private static long getTotalAmountOfPassengersTwoLoopsArray(int places, int noOfIterations, Integer[] queue, long totalGroupSize) {
		long totalAmountOfPassengers = 0;
		int idx = 0;
		int oldIdx;
		int currentPassengers = 0;

		for (int i = 0; i < noOfIterations; i++) {
			currentPassengers = 0;
			oldIdx = idx;
			while (totalGroupSize > currentPassengers) {
				if (queue[idx] > places - currentPassengers) {
					break;
				}

				currentPassengers += queue[idx];

				idx = (idx + 1) % queue.length;
				if (idx == oldIdx) {
					break;
				}

			}
			totalAmountOfPassengers += currentPassengers;
		}
		return totalAmountOfPassengers;
	}
}
