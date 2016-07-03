import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApuSecondSolutionTest extends OldAbstractTest {
	@Test
	public void getMatchesShouldMatch() {
		Set<String> result = ApuSecondSolution.getMatches("ab", "babba");
		Set<String> expected = new HashSet<>(Arrays.asList("ababba", "babba", "babbab"));

		Assert.assertEquals(expected, result);
	}

	@Test
	public void getMatchesShouldMatchTwo() {
		Set<String> expected = new HashSet<>(Arrays.asList("fofifafifafi", "fofifafifa"));

		Set<String> result = ApuSecondSolution.getMatches("fafi", "fofifafifa");
		Set<String> result2 = ApuSecondSolution.getMatches("fofifafifa", "fafi");

		Assert.assertEquals(expected, result);
		Assert.assertEquals(expected, result2);
	}

	@Test
	public void one() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_1_input.txt"));
		String expected = readFile("Test_1_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void two() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_2_input.txt"));
		String expected = readFile("Test_2_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void three() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_3_input.txt"));
		String expected = readFile("Test_3_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void four() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_4_input.txt"));
		String expected = readFile("Test_4_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void five() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_5_input.txt"));
		String expected = readFile("Test_5_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void six() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_6_input.txt"));
		String expected = readFile("Test_6_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void seven() throws IOException {
		String result = ApuSecondSolution.solve(buildScanner("Test_7_input.txt"));
		String expected = readFile("Test_7_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "genome/";
	}
}