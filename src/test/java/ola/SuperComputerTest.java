package ola;

import base.AbstractJunitParamsTest;

import java.util.Scanner;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * TODO write documentation
 */
public class SuperComputerTest extends AbstractJunitParamsTest {

	@Override
	protected Function<Scanner, String> getFunction() {
		return new SuperComputer()::solve;
	}
}