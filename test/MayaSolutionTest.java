import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MayaSolutionTest {

	public static final String DIR = "/Users/olasundell/code/codingame/src/maya/";

	@Test
	public void additionWithCarry() throws IOException {
		String result = MayaSolution.solve(new Scanner(new File(DIR + "maya-additionwithcarry.txt")));
		String expected = readFile("additionwithcarry-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void multiplication() throws IOException {
		String result = MayaSolution.solve(new Scanner(new File(DIR + "multiplication.txt")));
		String expected = readFile("multiplication-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void subtraction() throws IOException {
		String result = MayaSolution.solve(new Scanner(new File(DIR + "subtraction.txt")));
		String expected = readFile("subtraction-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void greatMultiplication() throws IOException {
		String result = MayaSolution.solve(new Scanner(new File(DIR + "great-multiplication.txt")));
		String expected = readFile("great-multiplication-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void zero() throws IOException {
		String result = MayaSolution.solve(new Scanner(new File(DIR + "zero.txt")));
		String expected = readFile("zero-solution.txt");
		Assert.assertEquals(expected, result);
	}


	//	@Test
	public void shouldGetNumber() {
		List<String> strings = new ArrayList<>();
		strings.add("o...");
		strings.add("____");
		strings.add("....");
		strings.add("....");
		strings.add("ooo.");
		strings.add("....");
		strings.add("....");
		strings.add("....");

		long num = MayaSolution.getNumber(strings, 4, 4);

		Assert.assertEquals(5, num);
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