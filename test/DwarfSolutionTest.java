import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class DwarfSolutionTest extends AbstractTest {

	@Test
	public void simple() throws IOException {
		String result = DwarfSolution.solve(buildScanner("simple.txt"));
		String expected = readFile("simple-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void complete() throws IOException {
		String result = DwarfSolution.solve(buildScanner("complete.txt"));
		String expected = readFile("complete-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void several() throws IOException {
		String result = DwarfSolution.solve(buildScanner("several.txt"));
		String expected = readFile("several-solution.txt");
		Assert.assertEquals(expected, result);
	}

	@Override
	protected String getDir() {
		return "dwarf/";
	}
}