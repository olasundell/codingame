import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ScrabbleSolutionTest {
	@Test
	public void shouldPermuteNaive() {
		List<String> result = ScrabbleSolution.permute("a");
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
	}

	@Test
	public void shouldPermuteSimple() {
		List<String> result = ScrabbleSolution.permute("ab");
		Assert.assertNotNull(result);
		Assert.assertEquals(4, result.size());
	}

	@Test
	public void shouldPermuteThree() {
		List<String> result = ScrabbleSolution.permute("abc");
		SortedSet<String> set = new TreeSet<>(result);
		Assert.assertNotNull(result);
		Assert.assertEquals(15, result.size());
	}

	@Test
	public void shouldNotContainTrapper() {
		List<String> result = ScrabbleSolution.permute("retpasn");
		Assert.assertNotNull(result);
		Assert.assertFalse(result.contains("trapper"));
	}

	@Test
	public void shouldContainPowers() {
		List<String> result = ScrabbleSolution.permute("tsropwe");
		Assert.assertTrue(result.contains("powers"));
	}
}