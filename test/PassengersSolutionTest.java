import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

public class PassengersSolutionTest extends AbstractTest {
	@Test
	public void large() throws IOException {
		Instant start = Instant.now();
		String result = PassengersSolution.solve(buildScanner("Test_6_input.txt"));
		Instant end = Instant.now();
		String expected = readFile("Test_6_output.txt");
		Assert.assertEquals(expected, result);
		final long millis = Duration.between(start, end).toMillis();
		Assert.assertTrue("Test took too long, it took " + millis, millis < 1000);
		System.out.println(millis);
		System.out.println(PassengersSolution.noOfTotalIters);
	}

	@Override
	protected String getDir() {
		return "rollercoaster/";
	}
}