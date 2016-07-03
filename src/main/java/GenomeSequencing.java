import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class GenomeSequencing {
    public String solve(Scanner in) {
        in.nextLine();

        PriorityQueue<String> genomes = new PriorityQueue<>((s1, s2) -> s2.length() - s1.length());

        while (in.hasNextLine()) {
            genomes.add(in.nextLine());
        }

        PriorityQueue<String> combinations = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());

        for (int i = 0 ; i < genomes.size() ; i++) {
            permute(genomes, combinations);
        }

        return String.valueOf(combinations.peek().length());
    }

    private void permute(PriorityQueue<String> genomes, PriorityQueue<String> combinations) {

//        combinations.addAll(combine(one, two));
    }

    private SortedSet<String> combine(String one, String two) {
        SortedSet<String> combinations = new TreeSet<>((s1, s2) -> s1.length() - s2.length());
        // superimpose
        for (int i = 0 ; i < one.length() - two.length() ; i++) {
            if (two.equals(one.substring(i, one.length() - i))) {
                combinations.add(one);
            }
        }
        // left to right
        for (int i = 0 ; i < two.length() ; i++) {
            String s = two.substring(two.length() - i);
            String remainder = two.substring(0, two.length() - i);
            String s1 = one.substring(0, i);

            if (s.equals(s1)) {
                combinations.add(remainder + one);
            }
        }
        // right to left
        for (int i = 0 ; i < two.length() ; i++) {
            String s = two.substring(0, i);
            String remainder = two.substring(i);
            String s1 = one.substring(one.length() - i);

            if (s.equals(s1)) {
                combinations.add(one + remainder);
            }
        }
        return combinations;
    }

    public static class Offset {
        public int start;
        public int finish;
    }
}
