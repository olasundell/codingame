import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.util.List;

/**
 * TODO write documentation
 */
public class PowerOfThorTest {

	private PowerOfThor powerOfThor;

	@Before
	public void setUp() throws Exception {
		powerOfThor = new PowerOfThor();
	}

	@Test
	public void bresenhamShouldWork() {
		List<Point> path = powerOfThor.bresenham(new Point(0,0), new Point(5,5));

		Assert.assertNotNull(path);
		Assert.assertFalse(path.isEmpty());
	}

	@Test
	public void bresenhamShouldReallyWork() {
		List<Point> path = powerOfThor.bresenham(new Point(0,0), new Point(3,7));

		Assert.assertNotNull(path);
		Assert.assertFalse(path.isEmpty());
	}

	@Test
	public void bresenhamVerticalShouldWork() {
		List<Point> path = powerOfThor.bresenham(new Point(0,0), new Point(0,5));

		Assert.assertNotNull(path);
		Assert.assertFalse(path.isEmpty());
	}

	@Test
	public void shouldReturnCorrectPath() {
		Assert.assertEquals("N", powerOfThor.direction(new Point(0,1), new Point(0,0)));
		Assert.assertEquals("NE", powerOfThor.direction(new Point(0,1), new Point(1,0)));
		Assert.assertEquals("E", powerOfThor.direction(new Point(0,1), new Point(1,1)));
		Assert.assertEquals("SE", powerOfThor.direction(new Point(0,0), new Point(1,1)));
		Assert.assertEquals("S", powerOfThor.direction(new Point(1,0), new Point(1,1)));
		Assert.assertEquals("SW", powerOfThor.direction(new Point(1,0), new Point(0,1)));
		Assert.assertEquals("W", powerOfThor.direction(new Point(1,0), new Point(0,0)));
		Assert.assertEquals("NW", powerOfThor.direction(new Point(1,1), new Point(0,0)));
	}
}