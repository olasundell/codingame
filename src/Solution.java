import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
	private static Map<Integer, List<Integer>> board = new HashMap<>();

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		solve(in);
	}

	protected static void solve(Scanner in) {
		int N = in.nextInt(); // the total number of nodes in the level, including the gateways
		int L = in.nextInt(); // the number of links
		int E = in.nextInt(); // the number of exit gateways

		List<Integer> gateways = new ArrayList<>();
		Set<Integer> gatewayNodes = new HashSet<>();
		List<Integer> doubleNodes = new ArrayList<>();

		for (int i = 0 ; i < N ; i ++) {
			board.put(i, new ArrayList<>());
		}

		for (int i = 0; i < L; i++) {
			int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
			int N2 = in.nextInt();

			board.get(N1).add(N2);
			board.get(N2).add(N1);
		}

		for (int i = 0; i < E; i++) {
			int EI = in.nextInt(); // the index of a gateway node
			gateways.add(EI);
			final List<Integer> nodes = board.get(EI);

			for (Integer n: nodes) {
				if (gatewayNodes.contains(n)) {
					doubleNodes.add(n);
				} else {
					gatewayNodes.add(n);
				}
			}
		}

		// game loop
		while (true) {
			int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

			// Write an action using System.out.println()
			// To debug: System.err.println("Debug messages...");

			PriorityQueue<List<Integer>> directions = new PriorityQueue<>((a, b) -> a.size() - b.size());

			for (Integer gw: gateways) {
				List<Integer> dir = getDirections(gw, SI);
				System.err.println("Adding directions " + dir);
				directions.add(dir);
			}

			System.err.println("Finished creating directions, it's " + directions);

			final List<Integer> first = directions.peek();

			// if the virus is distant, sever something doubly linked
			Integer gateway = -1;
			if (first.size() > 2 && !doubleNodes.isEmpty()) {
				List<Integer> probableDestinations = findProbableDestinations(doubleNodes, directions, SI);

				System.err.println("Probable destinations are " + probableDestinations);

				directions.clear();

				for (Integer i: doubleNodes) {
					for (Integer pd: probableDestinations) {
						final List<Integer> list = getDirections(i, pd);
						System.err.println("Probable direction to double node " + i + " is " + list);
						directions.add(list);
					}
				}

				System.err.println("Directions from probable destinations are " + directions);

				final Integer linkNode = directions.peek().get(0);
				final List<Integer> integers = board.get(linkNode);

				for (Integer i: integers) {
					if (gateways.contains(i)) {
						gateway = gateways.get(gateways.indexOf(i));
						final List<Integer> links = board.get(gateway);
						System.out.println(linkNode + " " + gateway);

						links.remove(linkNode);
						board.get(linkNode).remove(gateway);

						if (links.isEmpty()) {
							gateways.remove(gateway);
						}

						System.err.println("Removing double node " + linkNode);
						doubleNodes.remove(linkNode);
						break;
					}
				}
			} else {
				for (Integer gw: gateways) {
					directions.add(getDirections(gw, SI));
				}

				gateway = first.get(0);
				final List<Integer> links = board.get(gateway);
				final Integer link = first.get(1);
				System.out.println(link + " " + gateway);

				links.remove(link);
				board.get(link).remove(gateway);

				if (links.isEmpty()) {
					gateways.remove(gateway);
				}
			}

//            System.out.println("0 1"); // Example: 0 1 are the indices of the nodes you wish to sever the link between
		}
	}

	protected static List<Integer> findProbableDestinations(List<Integer> doubleNodes, PriorityQueue<List<Integer>> directions, int SI) {
		PriorityQueue<List<Integer>> probableDirections = new PriorityQueue<>((a, b) -> a.size() - b.size());

		System.err.println("Trying to get probable directions from " + directions);
		for (List<Integer> list : directions) {
			if (list.size() > directions.peek().size()) {
				System.err.print("list size");
				break;
			}

			probableDirections.add(getDirections(list.get(1), SI));
		}

//		for (Integer node: doubleNodes) {
//			System.err.println("Checking double node " + node + " with list " + list);
//			if (list.contains(node)) {
//				newDirections.add(getDirections(node, SI));
//			}
//		}

		final List<Integer> first = probableDirections.peek();
		List<Integer> probableDestinations = new ArrayList<>();

		for (List<Integer> list: probableDirections) {
			if (list.size() > first.size()) {
				break;
			}

			probableDestinations.add(list.get(list.size() - 2));
		}

		return probableDestinations;
	}

	protected static Map<Integer, Boolean> vis = new HashMap<>();

	protected static Map<Integer, Integer> prev = new HashMap<>();

	protected static List<Integer> getDirections(Integer start, Integer finish){
		vis.clear();
		prev.clear();

		List<Integer> directions = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		Integer current = start;
		q.add(current);
		vis.put(current, true);
		while(!q.isEmpty()){
			current = q.remove();
			if (current.equals(finish)){
				break;
			}else{
				for(Integer node : board.get(current)){
					if(!vis.containsKey(node)){
						q.add(node);
						vis.put(node, true);
						prev.put(node, current);
					}
				}
			}
		}
		if (!current.equals(finish)){
			System.err.println("can't reach destination");
		}
		for(Integer node = finish; node != null; node = prev.get(node)) {
			directions.add(node);
		}
		Collections.reverse(directions);
		return directions;
	}   
}