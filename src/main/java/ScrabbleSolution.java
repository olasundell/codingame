import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ScrabbleSolution {
	static Map<Character, Integer> scoreMap = new HashMap<>();

    public static void main(String args[]) {
	    setupScoreMap();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
	    List<String> dictionaryList = new ArrayList<>();
	    Set<String> dictionarySet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
	        dictionaryList.add(W);
	        dictionarySet.add(W);
        }
        String LETTERS = in.nextLine();

	    System.err.println("Letters are " + LETTERS);

	    Set<String> permutations = new HashSet<>(permute(LETTERS));

	    System.err.println("permutations size " + permutations.size());

	    SortedSet<String> words = new TreeSet<String>(new Comparator<String>() {
		    @Override
		    public int compare(String o1, String o2) {
			    final int i = calcScore(o1) - calcScore(o2);
				if (i == 0) {
					return - (dictionaryList.indexOf(o1) - dictionaryList.indexOf(o2));
				}
			    return i;
		    }
	    });

	    System.err.println("Dictionary contains " + dictionaryList.size());

	    final Set<String> collect = permutations.parallelStream().filter(dictionarySet::contains).collect(Collectors.toSet());

	    System.err.println("Found " + collect.size() + " words");

	    for (String w: new ArrayList<>(collect)){
		    System.err.println("Word: " + w + " score: " + calcScore(w));
	    }
	    words.addAll(collect);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
	    System.err.println(words.last() + " " + calcScore(words.last()));
	    System.out.println(words.last());
    }

	public static List<String> permute(String origStr) {
		int oldIdx, idx = 0;
		List<Permutation> permutations = new ArrayList<>();

		char[] chars = origStr.toCharArray();

		for (int i = 0 ; i < chars.length ; i++) {
			final Set<Integer> value = new HashSet<>();
			value.add(i);
			permutations.add(new Permutation(String.valueOf(chars[i]), value));
		}

		for (int i = 1 ; i < chars.length ; i++) {
			oldIdx = idx;
			idx = permutations.size();

			for (Permutation p : new ArrayList<>(permutations.subList(oldIdx, permutations.size()))) {
				for (int j = 0 ; j < chars.length ; j++) {
					if (!p.set.contains(j)) {
						final HashSet<Integer> value = new HashSet<>(p.set);
						value.add(j);
						permutations.add(new Permutation(p.str + chars[j], value));
					}
				}
			}
		}

		return permutations.stream().map(p -> p.str).collect(Collectors.toList());
	}

	static class Permutation {
		final String str;
		final Set<Integer> set;

		Permutation(String str, Set<Integer> set) {
			this.str = str;
			this.set = set;
		}
	}

	private static void setupScoreMap() {
		scoreMap.put('e', 1);
		scoreMap.put('a', 1);
		scoreMap.put('i', 1);
		scoreMap.put('o', 1);
		scoreMap.put('n', 1);
		scoreMap.put('r', 1);
		scoreMap.put('t', 1);
		scoreMap.put('l', 1);
		scoreMap.put('s', 1);
		scoreMap.put('u', 1);

		scoreMap.put('d', 2);
		scoreMap.put('g', 2);

		scoreMap.put('b', 2);
		scoreMap.put('c', 2);
		scoreMap.put('m', 2);
		scoreMap.put('p', 2);

		scoreMap.put('f', 4);
		scoreMap.put('h', 4);
		scoreMap.put('v', 4);
		scoreMap.put('w', 4);
		scoreMap.put('y', 4);

		scoreMap.put('k', 5);

		scoreMap.put('j', 8);
		scoreMap.put('x', 8);

		scoreMap.put('q', 10);
		scoreMap.put('z', 10);
	}

	private static Integer calcScore(String w) {
		List<Character> chars = new ArrayList<>();
		for (Character c: w.toCharArray()) {
			chars.add(c);
		}
		return chars.stream().mapToInt(ScrabbleSolution::scoreForChar).sum();
	}

	private static int scoreForChar(Character c) {
		return scoreMap.get(Character.toLowerCase(c));
	}
}