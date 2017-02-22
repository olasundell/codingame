import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class MagicStones {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new MagicStones().solve(in));
	}

	public String solve(Scanner in) {
		int n = in.nextInt();

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0 ; i < n ; i++) {
			int next = in.nextInt();
			map.put(next, map.getOrDefault(next, 0) + 1);
		}

		boolean change = true;

		while (change) {
			change = false;

			for (Map.Entry<Integer, Integer> entry : new HashSet<>(map.entrySet())) {
				int key = entry.getKey();

				if (entry.getValue() > 1) {
					int k = entry.getValue() / 2;
					map.put(key + 1, map.getOrDefault(key + 1, 0) + k);
					map.put(key, map.get(key) % 2);

					change = true;
				}
			}
		}

		return String.valueOf(map.values().stream().mapToInt(i -> i).sum());
	}
}
