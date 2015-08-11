import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractTest {
	protected final static String DIR = "/Users/olasundell/code/codingame/src/";
	protected abstract String getDir();

	protected Scanner buildScanner(String s) throws FileNotFoundException {
		return new Scanner(new File(DIR + getDir() + s));
	}

	protected String readFile(String fileName) throws IOException {
		List<String> solution = new ArrayList<>();
		String line;

		final BufferedReader reader = new BufferedReader(new FileReader(DIR + getDir() + fileName));

		while ((line = reader.readLine()) != null) {
			solution.add(line);
		}

		return String.join("\n", solution);
	}
}
