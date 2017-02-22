import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * TODO write documentation
 */
public class InertiaTest extends AbstractTest {
	@Test
	public void one() throws IOException {
		runTest(1, new Inertia()::solve);
	}

	@Test
	public void two() throws IOException {
		runTest(2, new Inertia()::solve);
	}

	@Test
	public void three() throws IOException {
		runTest(3, new Inertia()::solve);
	}

	@Test
	public void four() throws IOException {
		runTest(4, new Inertia()::solve);
	}

	@Test
	public void five() throws IOException {
		runTest(5, new Inertia()::solve);
	}

	@Test
	public void six() throws IOException {
		runTest(6, new Inertia()::solve);
	}

	@Test
	public void seven() throws IOException {
		runTest(7, new Inertia()::solve);
	}

	@Test
	public void eight() throws IOException {
		runTest(8, new Inertia()::solve);
	}
}