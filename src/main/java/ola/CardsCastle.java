package ola;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CardsCastle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new CardsCastle().solve(in));
	}

	public String solve(Scanner in) {
		int H = in.nextInt();
		in.nextLine();
		List<String> cards = new ArrayList<>();
		for (int i = 0; i < H; i++) {
			String S = in.nextLine();
			cards.add(S);
		}

		if (!cards.stream().allMatch(this::validRow)) {
			return "UNSTABLE";
		}

		List<RowMetaData> metadata = cards.stream().map(this::extractMetaData).collect(Collectors.toList());
		if (assertMetaData(metadata)) {
			return "STABLE";
		} else {
			return "UNSTABLE";
		}
	}

	protected boolean assertMetaData(List<RowMetaData> metadata) {
		for (int i = 1 ; i < metadata.size() ; i++) {
			RowMetaData previous = metadata.get(i - 1);
			RowMetaData current = metadata.get(i);

			for (Integer p: previous.closePoints) {
				if (!current.openPoints.contains(p)) {
					return false;
				}
			}

			for (Integer p: previous.openPoints) {
				if (!current.closePoints.contains(p)) {
					return false;
				}
			}
		}

		return true;
	}

	protected RowMetaData extractMetaData(String row) {
		RowMetaData rowMetaData = new RowMetaData();

		char[] chars = row.toCharArray();
		for (int i = 0; i < chars.length ; i++) {
			switch (chars[i]) {
				case '/':
					rowMetaData.openPoints.add(i);
					break;
				case '\\':
					rowMetaData.closePoints.add(i);
					break;
			}
		}

		return rowMetaData;
	}

	protected boolean validRow(String row) {
		char previous = '.';
		int open = 0;
		int close = 0;

		for (char c : row.toCharArray()) {
			switch (c) {
				case '/':
					open++;
					break;
				case '\\':
					close++;
					break;
			}

			if (c == '\\' && previous != '/') {
				return false;
			}
			previous = c;
		}

		return open == close;
	}

	protected static class RowMetaData {
		final Set<Integer> openPoints;
		final Set<Integer> closePoints;

		public RowMetaData() {
			openPoints = new HashSet<>();
			closePoints = new HashSet<>();
		}
	}
}
