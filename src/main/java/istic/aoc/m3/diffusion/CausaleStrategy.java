package istic.aoc.m3.diffusion;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

public class CausaleStrategy implements DiffusionStrategy {

    private Generator generator;
    private Observable<ObserverAsync<Generator>> observable;

    @Override
    public void configure(Generator generator, Observable<ObserverAsync<Generator>> observable) {
        this.generator = generator;
        this.observable = observable;
    }

    @Override
    public void execute() {
        this.observable.getObservers().forEach((it) -> it.update(this.generator));
    }

}
