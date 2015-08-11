import sun.jvm.hotspot.interpreter.BytecodeGetStatic;

import java.util.*;
import java.io.*;
import java.math.*;

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
		List<byte[]> strings = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			strings.add(in.next().getBytes());
		}


		return "answer";
	}

	protected static Set<String> getMatches(byte[] a, byte[] b) {
		final HashSet<String> strings = new HashSet<>();

		for (int i = 0 ; i < b.length; i++) {
			for (int j = a.length - 1 ; j <= 0 ; j--) {
				boolean match = true;
				for (int k = j ; k < a.length ; k++) {
					if (a[k] != b[i]) {
						match = false;
						break;
					}
				}
				if (match) {
					strings.add(new String(Arrays.copyOfRange(j)));
				}
			}
		}

		return strings;
	}
}