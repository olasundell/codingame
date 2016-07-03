import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class AsciiArtSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
	    Map<Character, Letter> letters = new HashMap<>();
	    List<List<String>> output = new ArrayList<>();

	    int L = in.nextInt();
        in.nextLine();
        int H = in.nextInt();
        in.nextLine();
        String T = in.nextLine();

	    for (char c = 'A' ; c <= 'Z' ; c++) {
			letters.put(c, new Letter(L, H, c));
	    }

	    letters.put('?', new Letter(L, H, '?'));

        for (int i = 0; i < H; i++) {
	        String line = in.nextLine();
	        for (char c = 'A' ; c <= 'Z' ; c++) {
		        final String substring = line.substring(c * L  - 'A' * L, c * L - 'A' * L + L);
		        letters.get(c).putLine(substring, i);
	        }
	        letters.get('?').putLine(line.substring(line.length() - L, line.length()), i);
	        output.add(new ArrayList<>());
        }

	    for (int i = 0 ; i < H ; i++) {
		    String line = "";
		    for (char c: T.toCharArray()) {
			    char upperCase = Character.toUpperCase(c);
			    if (upperCase  < 'A' || upperCase > 'Z') {
				    upperCase = '?';
			    }

			    line += letters.get(upperCase).toString(i);
		    }

		    System.out.println(line);
	    }
    }

	static class Letter {
		char[][] chars;
		char c;

		public Letter(int l, int h, char thisChar) {
			chars = new char[h][l];
			c = thisChar;
		}

		public void putLine(String line, int index) {
			if (line.length() != chars[index].length) {
				throw new IllegalArgumentException(c + " Line differs from expected array length, expected " +
						chars[index].length + " and got " + line.length());
			}

			for (int i = 0 ; i < chars[index].length ; i++) {
				chars[index][i] = line.charAt(i);
			}
		}

		public String toString(int line) {
			return String.valueOf(chars[line]);
		}
	}
}