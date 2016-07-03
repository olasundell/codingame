import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class MimeTypeSolution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();

	    Map<String, String> mimeTypes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
	        mimeTypes.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }
	    List<String> output = new ArrayList<>();

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
	        System.err.println(FNAME);
	        if (!FNAME.contains(".")) {
		        output.add("UNKNOWN");
//		        output.add(mimeTypes.getOrDefault(split[split.length - 1].toLowerCase(), "UNKNOWN"));
	        } else {
		        String ext = FNAME.substring(FNAME.lastIndexOf('.') + 1);
		        System.err.println(ext);
		        output.add(mimeTypes.getOrDefault(ext.toLowerCase(), "UNKNOWN"));
	        }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

	    output.stream().forEach(System.out::println);
//        System.out.println(); // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
    }
}