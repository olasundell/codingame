import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Winamax
 */
//class Solution {
//	public static void main(String args[]) {
//		Scanner in = new Scanner(System.in);
//		System.out.println(new WinamaxSolution().solve(in));
//	}
//}

class WinamaxSolution {
	final Deque<Card> firstPlayer = new ArrayDeque<>();
	final Deque<Card> secondPlayer = new ArrayDeque<>();

	public String solve(Scanner in) {
		int n = in.nextInt(); // the number of cards for player 1
		for (int i = 0; i < n; i++) {
			firstPlayer.add(new Card(in.next())); // the n cards of player 1
		}
		int m = in.nextInt(); // the number of cards for player 2
		for (int i = 0; i < m; i++) {
			secondPlayer.add(new Card(in.next())); // the m cards of player 2
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		return play().toString();
	}

	protected Result play() {
		Deque<Card> firstPlay = new ArrayDeque<>();
		Deque<Card> secondPlay = new ArrayDeque<>();

		Card one;
		Card two;

		int count = 0;

		for (;;) {
			System.err.println(firstPlayer.size() + ", " + firstPlayer);
			System.err.println(secondPlayer.size() + ", " + secondPlayer);
			System.err.println("----------");

			if (firstPlayer.isEmpty()) {
				return new Result(2, count);
			} else if (secondPlayer.isEmpty()) {
				return new Result(1, count);
			}

			one = firstPlayer.removeFirst();
			two = secondPlayer.removeFirst();

			if (one.compareTo(two) == 0) {
				if (firstPlayer.size() < 3) {
					return new Result(2, ++count);
				} else if (secondPlayer.size() < 3) {
					return new Result(1, ++count);
				}
				for (int i = 0 ; i < 3 ; i++) {
					firstPlay.add(firstPlayer.removeFirst());
					secondPlay.add(secondPlayer.removeFirst());
				}
			} else if (one.compareTo(two) > 0) {
				count++;
				firstPlayer.add(one);
				firstPlayer.addAll(firstPlay);
				firstPlayer.add(two);
				firstPlayer.addAll(secondPlay);

				firstPlay.clear();
				secondPlay.clear();
			} else {
				count++;
				secondPlayer.add(one);
				secondPlayer.addAll(firstPlay);
				secondPlayer.add(two);
				secondPlayer.addAll(secondPlay);
				firstPlay.clear();
				secondPlay.clear();
			}
		}
	}

	enum Suite {
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES;

		static Suite fromChar(Character c) {
			switch (c) {
				case 'C':
					return CLUBS;
				case 'D':
					return DIAMONDS;
				case 'H':
					return HEARTS;
				case 'S':
					return SPADES;

			}

			throw new IllegalArgumentException(c + " is not a valid suite.");
		}

		@Override
		public String toString() {
			switch (this) {
				case CLUBS:
					return "C";
				case DIAMONDS:
					return "D";
				case HEARTS:
					return "H";
				case SPADES:
					return "S";
			}

			throw new IllegalStateException("switch case for suite " + this.name() + " is missing");
		}
	}

	protected static class Card implements Comparable<Card> {
		final String value;
		final Suite suite;

		public Card(String s) {
			String val = s.charAt(0) == '1' ? s.substring(0, 2) : s.substring(0, 1);
			this.value = val;
			this.suite = Suite.fromChar(s.charAt(val.length()));
		}

		public Card(String value, Suite suite) {
			this.value = value;
			this.suite = suite;
		}

		int numericValue() {
			switch (value) {
				case "J":
					return 11;
				case "Q":
					return 12;
				case "K":
					return 13;
				case "A":
					return 14;
				default:
					return Integer.valueOf(String.valueOf(value));
			}
		}

		@Override
		public int compareTo(Card o) {
			return numericValue() - o.numericValue();
		}

		public String toString() {
			return String.valueOf(value) + suite;
		}
	}

	static class Result {
		static final int PAT = 0;

		final int won;
		final int rounds;

		public Result(int won, int rounds) {
			this.won = won;
			this.rounds = rounds;
		}

		public String toString() {
			if (won == PAT) {
				return "PAT";
			} else {
				return won + " " + rounds;
			}
		}
	}
}
