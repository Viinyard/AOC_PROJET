package istic.aoc.m3.generator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.diffusion.DiffusionStrategy;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

public class GeneratorImpl implements Generator, Observable<ObserverAsync<Generator>> {

    private static final Logger log = LoggerFactory.getLogger(GeneratorImpl.class);

    private final List<ObserverAsync<Generator>> observers = new ArrayList<>();
    
    private DiffusionStrategy strategy;

    private long value;

    @Override
    public long getValue() {
        return this.value;
    }
    
    
    public void setDiffusionStrategy(DiffusionStrategy strategy) {
        log.info("CHANGED STRATEGY : {} -> {}", this.strategy, strategy);
        this.strategy = strategy;
        this.strategy.configure(this, this);
    }
    
    @Override
    public void setValue(long value) {
        log.info("SET {}", this.value);
        this.value = value;
        this.notifyObservers();
    }
    
    @Override
    public void addObserver(ObserverAsync<Generator> o) {
        this.observers.add(o);
    }
    
    @Override
    public void removeObserver(ObserverAsync<Generator> o) {
        this.observers.add(o);
    }
    
    @Override
    public void notifyObservers() {
        this.strategy.execute();
        
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
    
    @Override
    public List<ObserverAsync<Generator>> getObservers() {
        return this.observers;
    }
}
