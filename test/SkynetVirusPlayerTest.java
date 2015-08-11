import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkynetVirusPlayerTest {

	public static final String DIR = "/Users/olasundell/code/codingame/src/skynetvirus/";

	@Test
	public void simple() throws IOException {
		SkynetVirusPlayer.solve(new Scanner(new File(DIR + "triplestar")));
	}

	private String readFile(String fileName) throws IOException {
		List<String> solution = new ArrayList<>();
		String line;

		final BufferedReader reader = new BufferedReader(new FileReader(DIR + fileName));

		while ((line = reader.readLine()) != null) {
			solution.add(line);
		}

		return String.join("\n", solution);
	}
}