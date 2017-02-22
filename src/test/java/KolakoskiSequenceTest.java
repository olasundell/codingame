import base.AbstractJunitParamsTest;

import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class KolakoskiSequenceTest extends AbstractJunitParamsTest {
	@Override
	protected Function<Scanner, String> getFunction() {
		return new KolakoskiSequence()::solve;
	}
}