package istic.aoc.m3.diffusion;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
        this.observable.getObservers().stream().forEach((it) -> it.update(this.generator));
    }
    
    
}
