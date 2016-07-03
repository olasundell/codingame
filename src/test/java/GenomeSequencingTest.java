import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * TODO write documentation
 */
public class GenomeSequencingTest extends OldAbstractTest {

    @Test
    public void one() throws IOException {
        runTest(1, new GenomeSequencing()::solve);
    }

    @Test
    public void two() throws IOException {
        runTest(2, new GenomeSequencing()::solve);
    }

    @Test
    public void time() {
        LocalTime time = LocalTime.of(0, 15);
        time = time.minus(45, ChronoUnit.MINUTES);
        String t = time.getHour() + " " + time.getMinute();

        Assert.assertEquals("23 30", t);
    }

    @Override
    protected String getDir() {
        return "genome/";
    }
}