import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StockExchangeSolutionTest extends AbstractTest {

	@Test
	public void one() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex1"));
		String expected = readFile("ex1-solution");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void two() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex2"));
		String expected = readFile("ex2-solution");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void three() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex3"));
		String expected = readFile("ex3-solution");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void four() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex4"));
		String expected = readFile("ex4-solution");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void five() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex5"));
		String expected = readFile("ex5-solution");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void six() throws IOException {
		String result = StockExchangeSolution.solve(buildScanner("ex6"));
		String expected = readFile("ex6-solution");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "stock/";
	}
}