import java.util.*;
import java.util.stream.Collectors;

class Solution {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println(new TanSolution().solve(in));
	}
}

class TanSolution {
	final Map<String, Stop> stops = new HashMap<>();
	final Map<Stop, List<Stop>> connections = new HashMap<>();


	public String solve(Scanner in) {

		String startPoint = in.next();
		in.nextLine();
		String endPoint = in.next();
		in.nextLine();
		int N = in.nextInt();
		in.nextLine();
		for (int i = 0; i < N; i++) {
			String STOP = in.nextLine();
			String[] arr = STOP.split(",");
			stops.put(arr[0], new Stop(arr));
		}

		int M = in.nextInt();
		in.nextLine();
		for (int i = 0; i < M; i++) {
			String ROUTE = in.nextLine();

			final Stop key = stops.get(ROUTE.split(" ")[0]);
			final String s = ROUTE.split(" ")[1];

			if (!connections.containsKey(key)) {
				connections.put(key, new ArrayList<>());
			}
			connections.get(key).add(stops.get(s));
		}

		List<Stop> path = aStar(stops.get(startPoint), stops.get(endPoint));

		if (path.isEmpty()) {
			return "IMPOSSIBLE";
		} else {
			return path.stream().map(s -> s.name).collect(Collectors.joining("\n"));
		}
	}

	List<Stop> aStar(Stop start, Stop goal) {
		Set<Stop> closed = new HashSet<>();
		Map<Stop, Stop> cameFrom = new HashMap<>();
		Map<Stop, Double> gScore = new HashMap<>();

		gScore.put(start, 0.0);

		Map<Stop, Double> fScore = new HashMap<>();

		PriorityQueue<Stop> open = new PriorityQueue<>((a, b) -> {
			if (a == null || b == null) {
				return 0;
			}

			return (int) (10_000 * fScore.getOrDefault(a, 0.0) - 10_000 * fScore.getOrDefault(b, 0.0));
		});

		open.add(start);

		fScore.put(start, gScore.get(start) + distance(start, goal));

		while (!open.isEmpty()) {
			Stop current = open.peek();
			if (current.equals(goal)) {
				return reconstructPath(cameFrom, goal);
			}

			open.remove(current);
			closed.add(current);

			for (Stop neighbor: connections.get(current)) {
				if (closed.contains(neighbor)) {
					continue;
				}

				double tentative_g_score = gScore.get(current) + distance(current, neighbor);

				if (!open.contains(neighbor) || tentative_g_score < gScore.get(neighbor)) {
					cameFrom.put(neighbor, current);
					gScore.put(neighbor, tentative_g_score);
					final double tentativeF = tentative_g_score + distance(neighbor, goal);
					fScore.put(neighbor, tentativeF);

					if (!open.contains(neighbor)){
						open.add(neighbor);
					}
				}
			}
		}

		return Collections.emptyList();
	}

	static List<Stop> reconstructPath(Map<Stop, Stop> cameFrom, Stop start) {
		List<Stop> totalPath = new ArrayList<>();
		totalPath.add(start);

		Stop current = start;

		while (cameFrom.containsKey(current)){
			current = cameFrom.get(current);
			totalPath.add(0, current);
		}

		return totalPath;
	}

	static class Stop {
		final String code;
		final String name;
		final double lat;
		final double lon;
		final int type;

		public Stop(String[] arr) {
			code = arr[0];
			name = arr[1].replaceAll("\"","");
			lat = Double.valueOf(arr[3]);
			lon = Double.valueOf(arr[4]);
			type = Integer.valueOf(arr[7]);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Stop stop = (Stop) o;
			return Objects.equals(code, stop.code);
		}
	}
	static double distance(Stop first, Stop second) {
		double dLat = Math.toRadians(second.lat - first.lat);
		double dLon = Math.toRadians(second.lon - first.lon);

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2)
				* Math.cos(Math.toRadians(first.lat)) * Math.cos(Math.toRadians(second.lat));
		double c = 2 * Math.asin(Math.sqrt(a));
		return 6371 * c;
	}

//	static double distance(Stop a, Stop b) {
//		double x = (b.lon - a.lon) * Math.cos((a.lat + b.lat) / 2);
//		double y = (b.lat - a.lat);
//
//		return Math.sqrt(x*x + y*y) * 6371;
//	}
}
