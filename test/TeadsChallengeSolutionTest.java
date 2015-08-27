import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TeadsChallengeSolutionTest extends AbstractTest {
//	@Test
//	public void shouldCalculateDistance() {
//		TeadsChallengeSolution.Node node = new TeadsChallengeSolution.Node(TeadsChallengeSolution.Node.NO_PARENT);
//
////		node.children.add(new Solution.Node(node));
//		final TeadsChallengeSolution.Node node1 = new TeadsChallengeSolution.Node(node);
//		node.children.add(node1);
//		node1.children.add(new TeadsChallengeSolution.Node(node1));
//		node1.children.add(new TeadsChallengeSolution.Node(node1));
//
//		Assert.assertEquals(2, TeadsChallengeSolution.largestDistance(node) - 1);
//		Assert.assertEquals(1, TeadsChallengeSolution.largestDistance(node1) - 1);
//	}

	@Test
	public void one() throws IOException {
		runTest(1, TeadsChallengeSolution::solve);
	}

	@Test
	public void two() throws IOException {
		runTest(2, TeadsChallengeSolution::solve);
	}

	@Test
	public void three() throws IOException {
		runTest(3, TeadsChallengeSolution::solve);
	}

	@Test
	public void four() throws IOException {
		runTest(4, TeadsChallengeSolution::solve);
	}

	@Test
	public void five() throws IOException {
		runTest(5, TeadsChallengeSolution::solve);
	}

	@Test
	public void six() throws IOException {
		runTest(6, TeadsChallengeSolution::solve);
	}

	@Test
	public void seven() throws IOException {
		runTest(7, TeadsChallengeSolution::solve);
	}

	@Test
	public void eight() throws IOException {
		runTest(8, TeadsChallengeSolution::solve);
	}

	@Test
	public void nine() throws IOException {
		runTest(9, TeadsChallengeSolution::solve);
	}

	@Test
	public void ten() throws IOException {
		runTest(10, TeadsChallengeSolution::solve);
	}

	@Override
	protected String getDir() {
		return "teads/";
	}
}