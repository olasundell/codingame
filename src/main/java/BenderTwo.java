import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * TODO write documentation
 */
class BenderTwo {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new BenderTwo().solve(in));
	}

	public String solve(Scanner in) {
		int n = Integer.valueOf(in.nextLine());
		List<Room> rooms = new ArrayList<>();

		for (int i = 0 ; i < n ; i++) {
			String[] line = in.nextLine().split(" ");
			int value = Integer.valueOf(line[1]);

			int left = -1;
			int right = -1;

			if (!"E".equals(line[2])) {
				left = Integer.valueOf(line[2]);
			}

			if (!"E".equals(line[3])) {
				right = Integer.valueOf(line[3]);
			}

			rooms.add(new Room(value, left, right));
		}

		return "";
	}

	//1  function Dijkstra(Graph, source):
	List <Room> dijkstra(List<Room> graph, Room source) {
//2      dist[source] ← 0                                    // Initialization
		Map<Room, Double> dist = new HashMap<>();
		Map<Room, Room> prev = new HashMap<>();

		dist.put(source, 0.0);

		PriorityQueue<Room> q = new PriorityQueue<>((o1, o2) -> (int)Math.signum(dist.get(o1) - dist.get(o2)));

		for (Room v: graph) {
			if (v != source) {
				dist.put(v, Double.POSITIVE_INFINITY);
				// TODO me
//9              prev[v] ← UNDEFINED                         // Predecessor of v
			}

			q.add(v);

			while (!q.isEmpty()) {
				Room u = q.poll();

				double alt = dist.get(u) + u.value + graph.get(u.one).value;

				if (alt < dist.get(v)) {
					dist.put(v, alt);
					prev.put(v, u);
				}

				alt = dist.get(u) + u.value + graph.get(u.two).value;

				if (alt < dist.get(v)) {
					dist.put(v, alt);
					prev.put(v, u);
				}

//21                 Q.decrease_priority(v, alt)
			}
		}

		Room p = source;
		List<Room> path = new ArrayList<>();

		while (prev.containsKey(p)) {
			path.add(p);
			p = prev.get(p);
		}
		return path;
	}


	static class Room {
		int one;
		int two;
		int value;

		public Room(int value, int left, int right) {
			this.value = value;
			this.one = one;
			this.two = two;
		}
	}
}
