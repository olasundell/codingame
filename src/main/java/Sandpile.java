import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
public class Sandpile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new Sandpile().solve(in));
	}

	public String solve(Scanner in) {
		int n = in.nextInt();
		in.nextLine();

		List<List<Integer>> first = getPile(in, n);
		List<List<Integer>> second = getPile(in, n);

		List<List<Integer>> added = add(first, second);

		Result pile = new Result(true, added);

		while (pile.changed) {
			pile = splay(pile);
		}

		return pile.toString();
	}

	private Result splay(Result pile) {
		List<List<Integer>> splayed = new ArrayList<>(pile.board);
		boolean changed = false;

		for (int i = 0 ; i < pile.board.size(); i++) {
			for (int j = 0 ; j < pile.board.size(); j++) {
				// after we've done one change, we'll just wait for the next iteration
				if (pile.board.get(i).get(j) > 3 && !changed) {
					splayed = splayInd(splayed, i, j);
					changed = true;
				}
			}
		}

		return new Result(changed, splayed);
	}

	private List<List<Integer>> splayInd(List<List<Integer>> added, int i, int j) {
		added.get(i).set(j, added.get(i).get(j) - 4);

		if (i != 0) {
			added.get(i - 1).set(j, added.get(i - 1).get(j) + 1);
		}

		if (i != added.size() - 1) {
			added.get(i + 1).set(j, added.get(i + 1).get(j) + 1);
		}

		if (j != 0) {
			added.get(i).set(j - 1, added.get(i).get(j - 1) + 1);
		}

		if (j != added.size() - 1) {
			added.get(i).set(j + 1, added.get(i).get(j + 1) + 1);
		}

		return added;
	}


	private class Result {
		final boolean changed;
		final List<List<Integer>> board;

		public Result(boolean changed, List<List<Integer>> board) {
			this.changed = changed;
			this.board = board;
		}

		public String toString() {
			return board.stream().map(l -> l.stream().map(String::valueOf).collect(Collectors.joining())).collect(Collectors.joining("\n"));
		}
	}

	private List<List<Integer>> add(List<List<Integer>> first, List<List<Integer>> second) {
		List<List<Integer>> added = new ArrayList<>();

		for (int i = 0 ; i < first.size(); i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0 ; j < first.size(); j++) {
				row.add(first.get(i).get(j) + second.get(i).get(j));
			}
			added.add(row);
		}

		return added;
	}

	private List<List<Integer>> getPile(Scanner in, int n) {
		List<List<Integer>> list = new ArrayList<>();

		for (int i = 0 ; i < n ; i++) {
			List<Integer> row = new ArrayList<>();

			for (char c: in.nextLine().toCharArray()) {
				row.add(c - '0');
			}
			list.add(row);
		}

		return list;
	}
}
