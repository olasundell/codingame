package ola;

import base.AbstractJunitParamsTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class CardsCastleTest extends AbstractJunitParamsTest {
	@Override
	protected Function<Scanner, String> getFunction() {
		return new CardsCastle()::solve;
	}

	@Test
	public void shouldValidateValidRow() {
		String row = ".../\\..";

		Assert.assertTrue(new CardsCastle().validRow(row));
	}

	@Test
	public void shouldValidateInvalidRow() {
		String row = "...//\\..";
		Assert.assertFalse(new CardsCastle().validRow(row));

		row = "...//\\\\..";
		Assert.assertFalse(new CardsCastle().validRow(row));

		row = ".../.\\..";
		Assert.assertFalse(new CardsCastle().validRow(row));

		row = "...../\\/......";
		Assert.assertFalse(new CardsCastle().validRow(row));
	}

	@Test
	public void shouldExtractMetaData() {
		CardsCastle.RowMetaData metadata = new CardsCastle().extractMetaData(".../\\..");
		Assert.assertNotNull(metadata);
		Assert.assertEquals(metadata.openPoints, new HashSet<Integer>() {{
			add(3);
		}});
		Assert.assertEquals(metadata.closePoints, new HashSet<Integer>() {{
			add(4);
		}});
	}
}