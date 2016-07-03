import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TanSolutionTest extends OldAbstractTest {
	@Test
	public void one() throws IOException {
		runTest(1);
	}

	@Test
	public void two() throws IOException {
		runTest(2);
	}

	@Test
	public void three() throws IOException {
		runTest(3);
	}

	@Test
	public void four() throws IOException {
		runTest(4);
	}

	@Test
	public void five() throws IOException {
		runTest(5);
	}

	@Test
	public void six() throws IOException {
		runTest(6);
	}

	private void runTest(int i) throws IOException {
		String result = new TanSolution().solve(buildScanner("Test_" + i + "_input.txt"));
		String expected = readFile("Test_" + i + "_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "tan/";
	}
}