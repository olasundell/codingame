import org.junit.Test;
import teads.TeadsChallengeSolution;

import java.io.IOException;

public class TeadsChallengeSolutionTest extends OldAbstractTest {
//	@Test
//	public void shouldCalculateDistance() {
//		teads.TeadsChallengeSolution.Node node = new teads.TeadsChallengeSolution.Node(teads.TeadsChallengeSolution.Node.NO_PARENT);
//
////		node.children.add(new Solution.Node(node));
//		final teads.TeadsChallengeSolution.Node node1 = new teads.TeadsChallengeSolution.Node(node);
//		node.children.add(node1);
//		node1.children.add(new teads.TeadsChallengeSolution.Node(node1));
//		node1.children.add(new teads.TeadsChallengeSolution.Node(node1));
//
//		Assert.assertEquals(2, teads.TeadsChallengeSolution.largestDistance(node) - 1);
//		Assert.assertEquals(1, teads.TeadsChallengeSolution.largestDistance(node1) - 1);
//	}

	@Test
	public void one() throws IOException {
		runTest(1, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void two() throws IOException {
		runTest(2, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void three() throws IOException {
		runTest(3, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void four() throws IOException {
		runTest(4, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void five() throws IOException {
		runTest(5, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void six() throws IOException {
		runTest(6, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void seven() throws IOException {
		runTest(7, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void eight() throws IOException {
		runTest(8, new TeadsChallengeSolution()::solve);
	}

//	@Test
	public void nine() throws IOException {
		runTest(9, new TeadsChallengeSolution()::solve);
	}

	@Test
	public void ten() throws IOException {
		runTest(10, new TeadsChallengeSolution()::solve);
	}

	@Override
	protected String getDir() {
		return "teads/";
	}
}