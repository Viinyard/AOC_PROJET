package istic.aoc.m3.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.Future;


@Ignore
public class ObservableGeneratorAsyncTest {

    private ObservableGeneratorAsync obsGeneratorAsync;

    @Before
    public void before() {
        // TODO instancier obsGeneratorAsync
    }

    @Test
    public void shouldProvideFutureNonNullWhenUpdated() {
        final Future<Void> future = this.obsGeneratorAsync.update(null);

        Assert.assertNotNull(future);
    }
}
