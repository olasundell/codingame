import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO write documentation
 */
public class WinamaxTest extends OldAbstractTest {
    @Test
    public void tenShouldWork() {
        WinamaxSolution.Card first = new WinamaxSolution.Card("10C");
        Assert.assertEquals(10, first.numericValue());
        Assert.assertEquals(WinamaxSolution.Suite.CLUBS, first.suite);
    }

    @Test
    public void compareCards() {
        WinamaxSolution.Card first = new WinamaxSolution.Card("A", WinamaxSolution.Suite.CLUBS);
        WinamaxSolution.Card second = new WinamaxSolution.Card("A", WinamaxSolution.Suite.DIAMONDS);
        Assert.assertEquals(0, first.compareTo(second));

        first = new WinamaxSolution.Card("K", WinamaxSolution.Suite.CLUBS);
        Assert.assertEquals(-1, first.compareTo(second));
    }
    @Test
    public void one() throws IOException {
        runTest(1, new WinamaxSolution()::solve);
    }

    @Test
    public void two() throws IOException {
        runTest(2, new WinamaxSolution()::solve);
    }

    @Test
    public void three() throws IOException {
        runTest(3, new WinamaxSolution()::solve);
    }

    @Test
    public void four() throws IOException {
        runTest(4, new WinamaxSolution()::solve);
    }

    @Test
    public void five() throws IOException {
        runTest(5, new WinamaxSolution()::solve);
    }

    @Test
    public void six() throws IOException {
        runTest(6, new WinamaxSolution()::solve);
    }

    @Test
    public void seven() throws IOException {
        runTest(7, new WinamaxSolution()::solve);
    }

    @Test
    public void eight() throws IOException {
        runTest(8, new WinamaxSolution()::solve);
    }

    @Override
    protected String getDir() {
        return "winamax/";
    }
}