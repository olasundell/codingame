import javafx.collections.transformation.SortedList;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/*
INITIALIZATION INPUT:
Line 1: 3 integers N L E
N, the total number of nodes in the level, including the gateways.
L, the number of links in the level.
E, the number of exit gateways in the level.
Next L lines: 2 integers per line (N1, N2), indicating a link between the nodes indexed N1 and N2 in the network.
Next E lines: 1 integer EI representing the index of a gateway node.
INPUT FOR ONE GAME TURN:
Line 1: 1 integer SI.
SI, is the index of the node on which the Skynet agent is positioned this turn
OUTPUT FOR ONE GAME TURN:
A single line comprised of two integers C1 and C2 seperated by a space. C1 and C2 are the indices of the nodes you wish to sever the link between.
CONSTRAINTS:
2 ≤ N ≤ 500
1 ≤ L ≤ 1000
1 ≤ E ≤ 20
0 ≤ N1, N2 < N
0 ≤ SI < N
0 ≤ C1, C2 < N
Response time per turn ≤ 150ms
 */
class SkynetVirusPlayer {

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
		}

		// game loop
		while (true) {
			int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

			// Write an action using System.out.println()
			// To debug: System.err.println("Debug messages...");

			SortedSet<List<Integer>> directions = new TreeSet<>((a, b) -> a.size() - b.size());

			for (Integer gw: gateways) {
				 directions.add(getDirections(gw, SI));
			}

			final Integer gateway = directions.first().get(0);
			final List<Integer> links = board.get(gateway);
			final Integer link = directions.first().get(1);

			System.out.println(link + " " + gateway);

			links.remove(link);
			board.get(link).remove(gateway);

			if (links.isEmpty()) {
				gateways.remove(gateway);
			}

//            System.out.println("0 1"); // Example: 0 1 are the indices of the nodes you wish to sever the link between
		}
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