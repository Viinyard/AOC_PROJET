package istic.aoc.m3.generator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Ignore
public class GeneratorAsyncTest {

    public GeneratorAsync generatorAsync;

    @Before
    public void beforeEach() {
        // TODO instancier le generateur
    }

    @Test
    public void shouldReturnFutureNonNull() {
        final Future<Long> future = this.generatorAsync.getValue();

        Assert.assertNotNull(future);
    }

    @Test
    public void shouldReturnValueNonNull() throws ExecutionException, InterruptedException {
        final Future<Long> future = this.generatorAsync.getValue();
        final Long value = future.get();

        Assert.assertNotNull(value);
    }
}
