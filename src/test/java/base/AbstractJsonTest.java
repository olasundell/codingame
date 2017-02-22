package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public abstract class AbstractJsonTest {
//	@Parameterized.Parameters
//	public static Collection<TestInfo> data() {
//		try {
//			return getInput().getTest().values();
//		} catch (IOException e) {
//			Assume.assumeNoException(e);
//		}
//
//		return Collections.emptyList();
//	}

//	@Parameterized.Parameter
//	public TestInfo param;
//
//	protected Function<Scanner, String> function;
//
//	@Test
//	public void test() {
//		String result = function.apply(new Scanner(param.getInput()));
//		Assert.assertEquals(param.getOutput(), result);
//	}
//
//	protected void runTest(int i, Function<Scanner, String> function) throws IOException {
//		Input input = getInput();
//		TestInfo testInfo = input.getTest().get(String.valueOf(i));
//		String result = function.apply(new Scanner(testInfo.getInput()));
//		Assert.assertEquals(testInfo.getOutput(), result);
//	}
//
//	private Input getInput() throws IOException {
//		String name = this.getClass().getName().replaceAll("Test", "") + ".json";
//		URL systemResource = ClassLoader.getSystemResource(name);
//		return new ObjectMapper().readValue(systemResource, Input.class);
//	}
//
//	@Builder
//	@Data
//	@JsonDeserialize(builder = Input.InputBuilder.class)
//	public static class Input {
//		private String title;
//		private String statement;
//		private Map<String, TestInfo> test;
//
//		@JsonPOJOBuilder(withPrefix = "")
//		public static class InputBuilder {}
//	}
//
//	@Builder
//	@Data
//	@JsonDeserialize(builder = TestInfo.TestInfoBuilder.class)
//	public static class TestInfo {
//		private String input;
//		private String title;
//		private String output;
//
//		@JsonPOJOBuilder(withPrefix = "")
//		public static class TestInfoBuilder {}
//	}
}
