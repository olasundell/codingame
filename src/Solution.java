import java.util.*;
import java.io.*;

public class Solution {
	private static Map<String, Integer> numeralMap  = new HashMap<>();
	private static Map<Integer, String> reverseNumeralMap  = new HashMap<>();
	public static void main(String args[]) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		System.out.println(solve(in));
	}

	public static String solve(Scanner in) {
		int L = in.nextInt();
		int H = in.nextInt();

		generateNumerals(in, L, H);

		int S1 = in.nextInt();
		List<String> firstNumerals = new ArrayList<>();
		for (int i = 0; i < S1; i++) {
			firstNumerals.add(in.next());
		}
		int S2 = in.nextInt();
		List<String> secondNumerals = new ArrayList<>();
		for (int i = 0; i < S2; i++) {
			secondNumerals.add(in.next());
		}
		String operation = in.next();

		long num1 = getNumber(firstNumerals, L, H);
		long num2 = getNumber(secondNumerals, L, H);
		long result = 0;

		switch (operation) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		return generateOutput(result, L);
	}

	protected static long getNumber(List<String> numeralArea, int numeralWidth, int numeralHeight) {
		String numeralArray[] = new String[numeralArea.size() / numeralHeight];

		for (int i = 0 ; i < numeralArray.length ; i++) {
			numeralArray[i] = "";
		}

		for (int i = 0 ; i < numeralArea.size() ; i++) {
			numeralArray[i / numeralHeight] += numeralArea.get(i);
		}

		long result = 0;

		for (int i = 0 ; i < numeralArray.length ; i++) {
			result += numeralMap.get(numeralArray[i]) * Math.pow(20, (numeralArray.length - (i + 1)));
		}

		return result;
	}

	protected static void generateNumerals(Scanner in, int numeralWidth, int numeralHeight) {
		String[] numeralLines = new String[numeralHeight];

		for (int i = 0 ; i < numeralHeight ; i++) {
			numeralLines[i] = in.next();
		}

		String[] numerals = createNumeralArray(numeralLines, numeralWidth, numeralHeight);

		Map<String, Integer> numMap = new HashMap<>();

		for (int i = 0 ; i < numerals.length ; i++) {
			numeralMap.put(numerals[i], i);
//			reverseNumeralMap.put(i, numerals[i]);
			reverseNumeralMap.put(i, String.join("\n", numerals[i].split("(?<=\\G.{" + numeralWidth + "})")));
		}
	}

	private static String[] createNumeralArray(String[] numeralLines, int numeralWidth, int numeralHeight) {
		String[] numerals = new String[(numeralLines[0].length() / numeralWidth)];

		for (int i = 0 ; i < numerals.length; i++) {
			numerals[i] = "";
		}

		for (int i = 0; i < numeralHeight; i++) {
			String[] numLine = numeralLines[i].split("(?<=\\G.{"+ numeralWidth +"})");

			for (int j = 0 ; j < numLine.length; j++) {
				numerals[j] = numerals[j] + numLine[j];
			}
		}
		return numerals;
	}

	private static String generateOutput(long result, int L) {
		List<String> strings = new ArrayList<>();
		long power = 1;
		long v = result / power;

		while (v >= 1) {
			strings.add(0, reverseNumeralMap.get((int) (v % 20)));

			power *= 20;

			v = result / power;
		}

		if (strings.isEmpty()) {
			strings.add(reverseNumeralMap.get(0));
		}

		return String.join("\n", strings);
	}
}
