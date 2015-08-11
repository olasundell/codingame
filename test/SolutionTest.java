import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionTest extends AbstractTest {
	@Test
	public void getMatchesShouldMatch() {
		Set<String> result = Solution.getMatches("ab".getBytes(), "babba".getBytes());
		Set<String> expected = new HashSet<>(Arrays.asList("ababba", "babba", "babbab"));

		Assert.assertEquals(expected, result);
	}
	@Test
	public void one() throws IOException {
		String result = Solution.solve(buildScanner("Test_1_input.txt"));
		String expected = readFile("Test_1_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "genome/";
	}
}