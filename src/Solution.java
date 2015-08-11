import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(solve(in));
    }

	public static String solve(Scanner in) {
		int N = in.nextInt();
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			strings.add(in.next());
		}

		Set<String> oldSubstrings = new HashSet<>();
		Set<String> newSubstrings = new HashSet<>();

		for (int i = 0 ; i < N ; i++) {
			for (int j = i + 1 ; j < N ; j++) {
				final String a = strings.get(i);
				final String b = strings.get(j);
				newSubstrings.addAll(getMatches(a, b));
			}
		}

		SortedSet<String> substrings = new TreeSet<>((a, b) -> a.length() - b.length());

		return String.valueOf(substrings.first().length());
	}

	protected static Set<String> matchSets(Set<String> one, Set<String> two) {
		return new HashSet<>();
	}

	protected static Set<String> getMatches(String a, String b) {
		final HashSet<String> strings = new HashSet<>();

		// trivial case
		if (a.contains(b)) {
			strings.add(a);
		} else if (b.contains(a)) {
			strings.add(b);
		}

		// forward
		for (int i = 0 ; i < a.length(); i++) {
			final String aSubstring = a.substring(i);
			if (b.indexOf(aSubstring) == 0) {
				strings.add(a.substring(0, i) + b);
			}
		}

		for (int i = 0 ; i < b.length(); i++) {
			final String bSubstring = b.substring(i);
			if (a.indexOf(bSubstring) == 0) {
				strings.add(b.substring(0, i) + a);
			}
		}

		return strings;
	}
}