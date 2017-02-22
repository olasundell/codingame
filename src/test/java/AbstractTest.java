import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public abstract class AbstractTest {
//	protected final static String DIR = "src/test/resources/";
//	protected String getDir() {
//		return this.getClass().getSimpleName().replaceAll("Test", "").toLowerCase();
//	}

	protected void runTest(int i, Function<Scanner, String> function) throws IOException {
		String result = function.apply(buildScanner(i + ".in"));
		String expected = readFile(i + ".ans");
		Assert.assertEquals(expected, result);
	}

	protected Scanner buildScanner(String s) throws FileNotFoundException {
//		return new Scanner(new File(DIR + getSafeDir() + s));
		String name = getDir() + "/" + s;
		InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(name);
		return new Scanner(systemResourceAsStream);
	}

	protected String getDir() {
		return this.getClass().getSimpleName().replaceAll("Test", "").toLowerCase();
	}

//	// TODO this could probably be slightly cleaner
//	protected String getSafeDir() {
//		String dir = getDir();
//
//		if (!dir.endsWith("/")) {
//			return dir + "/";
//		} else {
//			return dir;
//		}
//	}

	protected String readFile(String fileName) throws IOException {
		List<String> solution;

		try {
			URL systemResource = ClassLoader.getSystemResource(getDir() + "/" + fileName);
			URI uri = systemResource.toURI();
			solution = Files.readAllLines(Paths.get(uri));
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

//		final BufferedReader reader = new BufferedReader(new FileReader(getDir() + "/" + fileName));
//
//		while ((line = reader.readLine()) != null) {
//			solution.add(line);
//		}

		return String.join("\n", solution);
	}
}
