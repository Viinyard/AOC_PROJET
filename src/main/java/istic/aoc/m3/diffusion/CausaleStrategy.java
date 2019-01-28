package istic.aoc.m3.diffusion;

import istic.aoc.m3.active.Canal;
import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.generator.GeneratorImpl;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.ObserverAsync;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

public class CausaleStrategy implements DiffusionStrategy {

    private Generator generator;
    private Observable<ObserverAsync<Generator>> observable;
    
    private HashMap<ObserverAsync<Generator>, Generator> hmMemento;

    @Override
    public void configure(Generator generator, Observable<ObserverAsync<Generator>> observable) {
        this.generator = generator;
        this.observable = observable;
        this.hmMemento = new HashMap<>();
    }

    @Override
    public void execute() {
        this.observable.getObservers().forEach((it) -> {
            this.hmMemento.putIfAbsent(it, new MementoGenerator());
            Generator gen = this.hmMemento.get(it);
            gen.setValue(generator.getValue());
            it.update(gen);
        });
       
    }
    
    public class MementoGenerator implements Generator {
    
        private Queue<Long> mementoValue = new LinkedList<>();
        private long value;
        @Override
        public long getValue() {
            return this.mementoValue.poll();
        }
    
        @Override
        public void setValue(long value) {
            this.value = value;
            this.mementoValue.add(value);
        }
        
        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
        
        
    }
    
}
