import base.AbstractJunitParamsTest;

import java.util.Scanner;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * TODO write documentation
 */
public class MagicStonesTest extends AbstractJunitParamsTest {

	@Override
	protected Function<Scanner, String> getFunction() {
		return new MagicStones()::solve;
	}
}