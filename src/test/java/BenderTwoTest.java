import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO write documentation
 */
public class BenderTwoTest extends AbstractTest {

	private BenderTwo benderTwo;

	@Before
	public void setUp() throws Exception {
		benderTwo = new BenderTwo();
	}

	@Test
	public void one() throws IOException {
		runTest(1, benderTwo::solve);
	}

	@Test
	public void dijkstraShouldWork() {
		BenderTwo.Room source = new BenderTwo.Room(0, 1, 2);
		BenderTwo.Room one = new BenderTwo.Room(100, -1, -1);
		BenderTwo.Room two = new BenderTwo.Room(200, -1, -1);

		List<BenderTwo.Room> result = benderTwo.dijkstra(Arrays.asList(source, one, two), source);

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}
}