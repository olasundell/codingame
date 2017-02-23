import base.AbstractJunitParamsTest;

import java.util.Scanner;
import java.util.function.Function;

/**
 * TODO write documentation
 */
public class BustSpeedingVehiclesTest extends AbstractJunitParamsTest {

	@Override
	protected Function<Scanner, String> getFunction() {
		return new BustSpeedingVehicles()::solve;
	}
}