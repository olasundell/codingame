import org.junit.Assert;
import org.junit.Test;

public class TeadsChallengeSolutionTest {
	@Test
	public void shouldCalculateDistance() {
		TeadsChallengeSolution.Node node = new TeadsChallengeSolution.Node(TeadsChallengeSolution.Node.NO_PARENT);

//		node.children.add(new Solution.Node(node));
		final TeadsChallengeSolution.Node node1 = new TeadsChallengeSolution.Node(node);
		node.children.add(node1);
		node1.children.add(new TeadsChallengeSolution.Node(node1));
		node1.children.add(new TeadsChallengeSolution.Node(node1));

		Assert.assertEquals(2, TeadsChallengeSolution.largestDistance(node) - 1);
		Assert.assertEquals(1, TeadsChallengeSolution.largestDistance(node1) - 1);
	}
}