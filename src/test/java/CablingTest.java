import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

public class CablingTest extends OldAbstractTest {
	@Test
	public void distanceShouldWork() {
		int distance = Cabling.distance(new Point(0,0), new Point(2,2));
		Assert.assertEquals(4, distance);

		distance = Cabling.distance(new Point(0,1), new Point(2,2));
		Assert.assertEquals(3, distance);
	}
	@Test
	public void one() throws IOException {
		runTest(1);
	}

	@Test
	public void two() throws IOException {
		runTest(2);
	}

	private void runTest(int i) throws IOException {
		String result = Cabling.solve(buildScanner("Test_" + i + "_input.txt"));
		String expected = readFile("Test_" + 1 + "_output.txt");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "cabling/";
	}
}