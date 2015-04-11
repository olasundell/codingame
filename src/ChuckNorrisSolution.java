import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ChuckNorrisSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();

	    String bits = "";

	    for (byte b: MESSAGE.getBytes()) {
		    bits += getBits(b);
	    }

	    System.err.println(bits);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(translateBits(bits).trim());
    }

	private static String translateBits(String bits) {
		StringBuilder builder = new StringBuilder();
		boolean one = Integer.valueOf(String.valueOf(bits.charAt(0))) != 1;
		for (char c: bits.toCharArray()) {
			int bit = Integer.valueOf(String.valueOf(c));
			if (bit == 1) {
				if (one) {
					builder.append("0");
				} else {
					one = true;
					builder.append(" 0 0");
				}
			} else {
				if (!one) {
					builder.append("0");
				} else {
					one = false;
					builder.append(" 00 0");
				}

			}
		}

		return builder.toString();
	}

	private static String getBits(Byte b) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0 ; i < 7 ; i++) {
			final int pow = (int)Math.pow(2, i);
			final int bit = (b & pow) / pow;
			builder.insert(0, bit);
		}

		return builder.toString();
	}

}