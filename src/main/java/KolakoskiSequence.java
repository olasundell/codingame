import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
public class KolakoskiSequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(new KolakoskiSequence().solve(in));
	}

	public String solve(Scanner in) {
		int seqLength = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		List<Integer> sequence = new ArrayList<>();

		for (int i = 0 ; i < a ; i++) {
			if (sequence.size() >= seqLength) {
				return collect(sequence);
			}

			sequence.add(a);
		}

		int numTimes = a;
		if (a == 1) {
			numTimes = b;
		}

		for (int i = 0 ; i < numTimes ; i++) {
			if (sequence.size() >= seqLength) {
				return collect(sequence);
			}
			sequence.add(b);
		}

		int currentNum = a;
		int numIdx = 2;

		for (;;) {
			for (int i = 0 ; i < sequence.get(numIdx); i++) {
				if (sequence.size() >= seqLength) {
					return collect(sequence);
				}

				sequence.add(currentNum);
			}

			if (sequence.size() >= seqLength) {
				return collect(sequence);
			}

			if (currentNum == a) {
				currentNum = b;
			} else {
				currentNum = a;
			}
			numIdx++;
		}
	}

	private String collect(List<Integer> sequence) {
		return sequence.stream()
		.map(String::valueOf)
		.collect(Collectors.joining());
	}
}
