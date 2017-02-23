import base.AbstractJunitParamsTest;

import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class AncestorsDescendantsTest extends AbstractJunitParamsTest {

	@Override
	protected Function<Scanner, String> getFunction() {
		return new AncestorsDescendants()::solve;
	}
}