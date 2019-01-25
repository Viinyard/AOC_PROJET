package istic.aoc.m3.generator;

import java.util.concurrent.Future;

/**
 * Le générateur de nombre asynchrone
 */
public interface GeneratorAsync {

    /**
     * Fournit un future d'un long.
     *
     * @return Future de long
     */
    Future<Long> getValue();
    
}
