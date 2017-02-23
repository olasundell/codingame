import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO write documentation
 */
public class BustSpeedingVehicles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new BustSpeedingVehicles().solve(in));
	}

	public String solve(Scanner in) {
		int limit = in.nextInt();
		int n = in.nextInt();
		in.nextLine();
		Map<String, List<Reading>> readings = new HashMap<>();
		List<String> plates = new ArrayList<>();

		for (int i = 0 ; i < n ; i++) {
			String[] parts = in.nextLine().split(" ");
			final Reading reading = new Reading(parts[0], parts[1], parts[2]);

			if (!readings.containsKey(parts[0])) {
				plates.add(parts[0]);
			}

			final List<Reading> list = readings.getOrDefault(parts[0], new ArrayList<>());
			list.add(reading);

			readings.put(parts[0], list);
		}

		List<Reading> results = plates.stream()
				.map(readings::get)
				.flatMap(r -> findSpeeding(r, limit))
				.collect(Collectors.toList());

		if (results.isEmpty()) {
			return "OK";
		} else {
			return results.stream()
					.map(r -> r.getPlate() + " " + r.mark)
					.collect(Collectors.joining("\n"));
		}
	}

	private Stream<Reading> findSpeeding(List<Reading> readings, int limit) {
		List<Reading> speedings = new ArrayList<>();

		for (int i = 0 ; i < readings.size() - 1 ; i++) {
			if (speed(readings.get(i), readings.get(i + 1)) > limit) {
				speedings.add(readings.get(i + 1));
			}
		}

		return speedings.stream();
	}

	protected double speed(Reading one, Reading two) {
		double distance = two.mark - one.mark;
		double time = two.instant.getEpochSecond() - one.instant.getEpochSecond();

		return distance / (time / 3_600);
	}

	public static class Reading {
		private final String plate;
		private final Instant instant;
		private final int mark;

		public Reading(String plate, String mark, String instant) {
			this(plate, Integer.valueOf(mark), Instant.ofEpochSecond(Long.valueOf(instant)));
		}

		public Reading(String plate, int mark, Instant instant) {
			this.plate = plate;
			this.instant = instant;
			this.mark = mark;
		}

		public String getPlate() {
			return plate;
		}
	}
}
