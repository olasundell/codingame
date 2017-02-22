package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public abstract class AbstractTestNGJsonTest {
	protected abstract Function<Scanner, String> getFunction();

//	@DataProvider(name = "testInput")
//	public Object[][] dataProvider() {
//
//	}

//	public void test() {
//		String result = function.apply(new Scanner(param.getInput()));
//		Assert.assertEquals(param.getOutput(), result);
//	}

//	protected void runTest(int i, Function<Scanner, String> function) throws IOException {
//		Input input = getInput();
//		TestInfo testInfo = input.getTest().get(String.valueOf(i));
//		String result = function.apply(new Scanner(testInfo.getInput()));
//		Assert.assertEquals(testInfo.getOutput(), result);
//	}

	private Input getInput() throws IOException {
		String name = this.getClass().getName().replaceAll("Test", "") + ".json";
		URL systemResource = ClassLoader.getSystemResource(name);
		return new ObjectMapper().readValue(systemResource, Input.class);
	}

}
