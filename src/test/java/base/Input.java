package base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * TODO write documentation
 */
@Builder
@Data
@JsonDeserialize(builder = Input.InputBuilder.class)
public class Input {
	private String title;
	private String statement;
	private Map<Integer, TestInfo> test;

	@JsonPOJOBuilder(withPrefix = "")
	public static class InputBuilder {
	}
}
