import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.ConversionFailedException;
import junitparams.converters.Converter;
import junitparams.converters.Param;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.*;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Scanner;

/**
 * TODO write documentation
 */
@RunWith(JUnitParamsRunner.class)
public class BenderTest extends AbstractTest {

	private Bender bender;


	@Before
	public void setUp() throws Exception {
		bender = new Bender();
	}

	@Test
	public void one() throws IOException {
		runTest(1, bender::solve);
	}

	@Test
	public void three() throws IOException {
		runTest(3, bender::solve);
	}

	@Test
	public void nine() throws IOException {
		runTest(9, bender::solve);
	}

	@Test
	public void twelve() throws IOException {
		runTest(12, bender::solve);
	}

	public static class MapToStateConverter implements Converter<State, Bender.State> {

		@Override
		public void initialize(State annotation) {

		}

		@Override
		public Bender.State convert(Object param) throws ConversionFailedException {
			return new Bender().readMap(new Scanner(String.valueOf(param)));
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	@Param(converter = MapToStateConverter.class)
	public @interface State {
	}

	@Test
	@Parameters(
			"#########\n" +
			"#   BXBX#\n" +
			"#X @    #\n" +
			"#       #\n" +
			"#########")
	public void shouldSetStartingPointProperly(@State Bender.State state) {
		Assert.assertEquals(new Point(3, 2), state.currentPos);
	}

	@Test
	@Parameters(
			"#########\n" +
			"# T     #\n" +
			"#       #\n" +
			"#       #\n" +
			"#  @    #\n" +
			"#  T    #\n" +
			"#########"
	)
	public void shouldFindTeleporters(@State Bender.State state) {
		Point one = new Point(2, 1);
		Point two = new Point(3, 5);
		Assert.assertTrue(state.teleporters.containsKey(one));
		Assert.assertTrue(state.teleporters.containsKey(two));
		Assert.assertEquals(two, state.teleporters.get(one));
		Assert.assertEquals(one, state.teleporters.get(two));
	}

	@Test
	@Parameters(
			"#########\n" +
			"#   BXBX#\n" +
			"#X@     #\n" +
			"#       #\n" +
			"#########")
	public void shouldCreateSimpleMove(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);

		Assert.assertEquals(new Point(2, 3), move.newPos);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#   BXBX#\n" +
			"#X      #\n" +
			"# @     #\n" +
			"#########")
	public void shouldCollideWithWall(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);

		Assert.assertEquals(new Point(3,3), move.newPos);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#@  BXBX#\n" +
			"#X      #\n" +
			"#       #\n" +
			"#########")
	public void shouldCollideWithObstacle(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);

		Assert.assertEquals(new Point(2, 1), move.newPos);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#   BXBX#\n" +
			"#X@     #\n" +
			"#       #\n" +
			"#########")
	public void shouldExecuteMove(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);

		Assert.assertEquals(new Point(2, 3), move.newPos);

		bender.executeMove(move, state);

		Assert.assertEquals(move.newPos, state.currentPos);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#@  BXBX#\n" +
			"#X      #\n" +
			"#       #\n" +
			"#########")
	public void shouldSmashWhileOnBeer(@State Bender.State state) {
		state.beer = true;

		Bender.Move move = bender.createMove(state);

		Assert.assertEquals(new Point(1, 2), move.newPos);

		bender.executeMove(move, state);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#  @    #\n" +
			"#  B    #\n" +
			"#  X    #\n" +
			"#  B    #\n" +
			"#  X    #\n" +
			"#########")
	public void shouldSetBeerThenSmashThenUnsetOnNewAndCollide(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);
		bender.executeMove(move, state);

		Assert.assertTrue("Should have set beer state", state.beer);

		move = bender.createMove(state);

		Assert.assertEquals("Should not move sideways", state.currentPos.x, move.newPos.x);
		Assert.assertEquals("Should move one step down", state.currentPos.y + 1, move.newPos.y);

		bender.executeMove(move, state);
		Assert.assertEquals(Bender.Type.BLANK, state.getCurrentTile().type);

		move = bender.createMove(state);

		Assert.assertEquals("Should not move sideways", state.currentPos.x, move.newPos.x);
		Assert.assertEquals("Should move one step down", state.currentPos.y + 1, move.newPos.y);

		bender.executeMove(move, state);

		Assert.assertFalse("Should no longer be beery", state.beer);

		move = bender.createMove(state);

		Assert.assertEquals("Should move sideways", state.currentPos.x + 1, move.newPos.x);
		Assert.assertEquals("Should collide with obstacle", state.currentPos.y, move.newPos.y);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#  @    #\n" +
			"#  I    #\n" +
			"#       #\n" +
			"#       #\n" +
			"#       #\n" +
			"#########"
	)
	public void shouldFlipPriorities(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);
		bender.executeMove(move, state);

		Assert.assertEquals(Bender.Direction.WEST, state.priorities.get(0));
	}

	@Test
	@Parameters(
			"#########\n" +
			"#T      #\n" +
			"#       #\n" +
			"#       #\n" +
			"#  @    #\n" +
			"#  T    #\n" +
			"#########"
	)
	public void shouldTeleport(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);
		bender.executeMove(move, state);

		Assert.assertEquals(new Point(1, 1), state.currentPos);

	}
	@Test
	@Parameters(
			"#########\n" +
			"#       #\n" +
			"#  @    #\n" +
			"#  W    #\n" +
			"#       #\n" +
			"#       #\n" +
			"#########"
	)
	public void shouldActOnDirectionalTiles(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);
		bender.executeMove(move, state);

		move = bender.createMove(state);

		Assert.assertEquals(state.currentPos.x - 1, move.newPos.x);
		Assert.assertEquals(state.currentPos.y, move.newPos.y);
	}

	@Test
	@Parameters(
			"#########\n" +
			"#  @    #\n" +
			"#  $    #\n" +
			"#       #\n" +
			"#       #\n" +
			"#       #\n" +
			"#########"
	)
	public void shouldCommitSuicide(@State Bender.State state) {
		Bender.Move move = bender.createMove(state);
		bender.executeMove(move, state);

		Assert.assertFalse("Should have committed suicide", state.isAlive());
	}
}