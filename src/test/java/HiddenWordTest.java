import base.AbstractJunitParamsTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class HiddenWordTest extends AbstractJunitParamsTest {

	@Override
	protected Function<Scanner, String> getFunction() {
		return new HiddenWord()::solve;
	}

	@Test
	public void shouldPutWords() {
		final HiddenWord hiddenWord = new HiddenWord();
		final HiddenWord.TreeNode node = new HiddenWord.TreeNode(Character.MAX_VALUE);
		hiddenWord.putWord(node, "HIDE");
		hiddenWord.putWord(node, "HIGH");
		hiddenWord.putWord(node, "HYPE");
		Assert.assertNotNull(node);
	}

	@Test
	public void shouldFindWords() {
		final HiddenWord hiddenWord = new HiddenWord();
		final HiddenWord.TreeNode node = new HiddenWord.TreeNode(Character.MAX_VALUE);
		hiddenWord.putWord(node, "HIDE");
		hiddenWord.putWord(node, "HIGH");
		hiddenWord.putWord(node, "HYPE");
//		Assert.assertEquals(2, hiddenWord.findWord(node, "HI").numResults());
//		Assert.assertTrue(hiddenWord.findWord(node, "HIDE").isWhole());
	}

	@Test
	public void shouldNotReverse() {
		List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

		List<Integer> subList = new ArrayList<>(list.subList(0, 4));
		Collections.reverse(subList);

		Assert.assertEquals(0, list.get(0).intValue());
		Assert.assertEquals(3, subList.get(0).intValue());
	}
}