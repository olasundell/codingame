import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public abstract class AbstractTest {
	protected final static String DIR = "/Users/olasundell/code/codingame/src/";
	protected abstract String getDir();

	protected void runTest(int i, Function<Scanner, String> function) throws IOException {
		String result = function.apply(buildScanner("Test_" + i + "_input.txt"));
		String expected = readFile("Test_" + i + "_output.txt");
		Assert.assertEquals(expected, result);
	}

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
