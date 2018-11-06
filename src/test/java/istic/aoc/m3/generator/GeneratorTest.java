package istic.aoc.m3.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore(value = "pas d'implémentation disponible du générateur pour le moment")
public class GeneratorTest {

    private Generator generator;


    @Before
    public void before() {
        // TODO instancier le générateur
    }

    @Test
    public void shouldNeverReturnNullValue() {
        final Long value = this.generator.getValue();

        Assert.assertNotNull(value);
    }


}
