import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class CgxSolutionTest extends OldAbstractTest {
	@Test
	public void one() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_1_input.txt"));
		String expected = readFile("Test_1_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void two() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_2_input.txt"));
		String expected = readFile("Test_2_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void three() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_3_input.txt"));
		String expected = readFile("Test_3_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void four() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_4_input.txt"));
		String expected = readFile("Test_4_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void five() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_5_input.txt"));
		String expected = readFile("Test_5_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void six() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_6_input.txt"));
		String expected = readFile("Test_6_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void seven() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_7_input.txt"));
		String expected = readFile("Test_7_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void eight() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_8_input.txt"));
		String expected = readFile("Test_8_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void nine() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_9_input.txt"));
		String expected = readFile("Test_9_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void ten() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_10_input.txt"));
		String expected = readFile("Test_10_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void eleven() throws IOException {
		String result = CgxSolution.solve(buildScanner("Test_11_input.txt"));
		String expected = readFile("Test_11_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void twelve() throws IOException {
		Instant start = Instant.now();

		String result = CgxSolution.solve(buildScanner("Test_12_input.txt"));
		Instant end = Instant.now();
		String expected = readFile("Test_12_output.txt");
		System.err.println(Duration.between(start, end).toMillis());
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "cgx/";
	}
}