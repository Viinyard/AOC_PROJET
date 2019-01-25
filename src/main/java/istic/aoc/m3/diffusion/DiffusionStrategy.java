package istic.aoc.m3.diffusion;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

public interface DiffusionStrategy {

    void configure(Generator generator, Observable<ObserverAsync<Generator>> observable);
    
    void execute();
}
