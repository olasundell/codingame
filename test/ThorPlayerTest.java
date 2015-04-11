import org.junit.Assert;
import org.junit.Test;

public class ThorPlayerTest {
	@Test
	public void shouldFindOctant() {
		ThorPlayer.Transform transform = new ThorPlayer.Transform(0, 0, 2, 2);
		Assert.assertEquals(0, transform.findOctant(0, 3, 2, 0));
		Assert.assertEquals(1, transform.findOctant(0, 1, 2, 0));
	}

}