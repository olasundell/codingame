package base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

/**
 * TODO write documentation
 */
@Builder
@Data
@JsonDeserialize(builder = TestInfo.TestInfoBuilder.class)
public class TestInfo {
	private String input;
	private String title;
	private String output;

	@JsonPOJOBuilder(withPrefix = "")
	public static class TestInfoBuilder {
	}
}
