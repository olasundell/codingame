package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import ola.CardsCastle;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO write documentation
 */
@RunWith(JUnitParamsRunner.class)
public abstract class AbstractJunitParamsTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	@Parameters(method = "readData")
	@TestCaseName("{index}")
	public void runTest(int idx, TestInfo testInfo) {
		String result = getFunction().apply(new Scanner(testInfo.getInput()));
		Assert.assertEquals(testInfo.getOutput().trim(), result);
	}

	public Object readData() {
		String name = "json/" + this.getClass().getSimpleName().replaceAll("Test", "") + ".json";
		URL systemResource = ClassLoader.getSystemResource(name);
		try {
			Input input = new ObjectMapper().readValue(systemResource, Input.class);
			return input.getTest()
					.entrySet()
					.stream()
					.sorted(Comparator.comparing(Map.Entry::getKey))
					.map(e -> new Object[] { e.getKey(), e.getValue() })
					.collect(Collectors.toList());
		} catch (IOException e) {
			Assume.assumeNoException(e);
		}

		return Collections.emptyList();
	}

	protected abstract Function<Scanner, String> getFunction();
}
