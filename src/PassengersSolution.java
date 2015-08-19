
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 *
 */
class PassengersSolution {
	public static Instant beforeRead;
	public static Instant afterRead;
	public static long noOfTotalIters = 0;

    public static void main(String args[]) throws FileNotFoundException {
	    Scanner in = new Scanner(System.in);
	    System.out.println(solve(in));
    }

	protected static String solve5(Scanner in) {
		int places = in.nextInt();
		int noOfIterations = in.nextInt();
		int noOfGroups = in.nextInt();

		final Deque<Integer> groups = new ArrayDeque<>();
		for (int i = 0; i < noOfGroups; i++) {

		}
		return "";
	}

	protected static String solve(Scanner in) {
		int places = in.nextInt();
		int noOfIterations = in.nextInt();
		int noOfGroups = in.nextInt();

		final Deque<Ride> groups = new ArrayDeque<>();
		Ride currentRide = new Ride();

		for (int i = 0; i < noOfGroups; i++) {
			int Pi = in.nextInt();
			if (currentRide.size() + Pi > places) {
				groups.add(currentRide);
				currentRide = new Ride();
			}
			currentRide.add(Pi);
		}

		if (currentRide.size() > 0) {
			groups.add(currentRide);
		}

		long totalNoOfPax = 0;

		for (int i = 0 ; i < noOfIterations ; i++) {
			Ride ride = groups.removeFirst();
			totalNoOfPax += ride.size();

			Ride last = groups.getLast();
			while (last.size() + ride.peekFirst() <= places) {
				last.add(ride.removeFirst());
			}

			groups.addLast(ride);
		}

		return String.valueOf(totalNoOfPax);
	}

	static class Ride {
		Deque<Integer> groups = new ArrayDeque<>();

		long currentMembers = 0;

		public void add(int members) {
			groups.add(members);
			currentMembers += members;
		}

		public long size() {
			return currentMembers;
		}

		public long peekFirst() {
			return groups.peekFirst();
		}

		public int removeFirst() {
			final Integer integer = groups.removeFirst();
			currentMembers -= integer;
			return integer;
		}
	}

	protected static String solve3(Scanner in) {
		int places = in.nextInt();
		int noOfIterations = in.nextInt();
		int noOfGroups = in.nextInt();

		System.err.println("Starting");
		List<Integer> queue = new ArrayList<>();
		long totalGroupSize = 0;
		long currentGroupSize = 0;
		int currentNoOfIter = 0;
		long currentAmountOfPassengers = 0;
		int idx = 0;

		beforeRead = Instant.now();

		for (int i = 0; i < noOfGroups; i++) {
			int Pi = in.nextInt();
			queue.add(Pi);
			totalGroupSize += Pi;
			if (currentGroupSize + Pi > places) {
				currentAmountOfPassengers += currentGroupSize;
				currentNoOfIter++;
				currentGroupSize = Pi;
				idx = i;

				if (currentNoOfIter >= noOfIterations) {
					break;
				}
			}
		}

		long totalAmountOfPassengers = currentAmountOfPassengers;

		afterRead = Instant.now();

		if (currentNoOfIter < noOfIterations) {
			totalAmountOfPassengers += getTotalAmountOfPassengers(places,
					noOfIterations - currentNoOfIter,
					queue,
					totalGroupSize - currentAmountOfPassengers,
					idx);
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
		return String.valueOf(totalAmountOfPassengers);
	}

	protected static String solve2(Scanner in) {
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

	    long totalAmountOfPassengers = getTotalAmountOfPassengers(places, noOfIterations, queue, totalGroupSize, 0);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        return String.valueOf(totalAmountOfPassengers);
    }

	private static long getTotalAmountOfPassengers(int places, int noOfIterations, List<Integer> queue, long totalGroupSize, int idx) {
//		return getTotalAmountOfPassengersOneLoop(places, noOfIterations, queue, totalGroupSize);
		return getTotalAmountOfPassengersTwoLoops(places, noOfIterations, queue, totalGroupSize, idx);
//		return getTotalAmountOfPassengersTwoLoopsArray(places, noOfIterations, queue.toArray(new Integer[queue.size()]), totalGroupSize);

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

	private static long getTotalAmountOfPassengersTwoLoops(int places,
	                                                       int noOfIterations,
	                                                       List<Integer> queue,
	                                                       long totalGroupSize,
	                                                       int initialIdx) {
		long totalAmountOfPassengers = 0;
		int idx = initialIdx;
		int oldIdx;
		int currentPassengers = 0;
		final int size = queue.size();

		for (int i = 0; i < noOfIterations; i++) {
		    currentPassengers = 0;
		    oldIdx = idx;
		    while (totalGroupSize > currentPassengers) {
		        if (queue.get(idx) > places - currentPassengers) {
		            break;
		        }

		        currentPassengers += queue.get(idx);

			    idx = (idx + 1) % size;
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
