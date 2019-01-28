package istic.aoc.m3.diffusion;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SequentielStrategy implements DiffusionStrategy, Generator {
    
    private Generator generator;
    private Observable<ObserverAsync<Generator>> observable;
    List<Future<Void>> enAttentes;
    
    private long value;
    
    @Override
    public void configure(Generator generator, Observable<ObserverAsync<Generator>> observable) {
        this.generator = generator;
        this.observable = observable;
        this.enAttentes = new ArrayList<>();
    }
    
    @Override
    public void execute() {
        this.value = this.generator.getValue();
        if(this.enAttentes.stream().allMatch(f -> f.isDone())) {
            this.enAttentes = this.observable.getObservers().stream()
                .map((it) -> it.update(this))
                .collect(Collectors.toList());
        }
    }
    
    @Override
    public long getValue() {
        
        return this.value;
    }
    
    @Override
    public void setDiffusionStrategy(DiffusionStrategy strategy) {
    
    }
    
    @Override
    public void setValue(long value) {
    
    }
}
