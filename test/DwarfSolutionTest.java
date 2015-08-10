import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DwarfSolutionTest {
	public static final String DIR = "/Users/olasundell/code/codingame/src/dwarf/";

	@Test
	public void simple() throws IOException {
		String result = DwarfSolution.solve(new Scanner(new File(DIR + "simple.txt")));
		String expected = readFile("simple-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void complete() throws IOException {
		String result = DwarfSolution.solve(new Scanner(new File(DIR + "complete.txt")));
		String expected = readFile("complete-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void several() throws IOException {
		String result = DwarfSolution.solve(new Scanner(new File(DIR + "several.txt")));
		String expected = readFile("several-solution.txt");
		Assert.assertEquals(expected, result);
	}

	private String readFile(String fileName) throws IOException {
		List<String> solution = new ArrayList<>();
		String line;

		final BufferedReader reader = new BufferedReader(new FileReader(DIR + fileName));

		while ((line = reader.readLine()) != null) {
			solution.add(line);
		}

		return String.join("\n", solution);
	}

}