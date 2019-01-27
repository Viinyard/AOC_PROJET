package istic.aoc.m3.active;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.generator.GeneratorAsync;
import istic.aoc.m3.observer.Observable;
import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.ObserverAsync;

/**
 * @author VinYarD
 * created : 17/10/2018, 14:41
     */
public class Canal implements ObserverAsync<Generator>, Observable<Observer<GeneratorAsync>>, GeneratorAsync {

    private final Logger log = LoggerFactory.getLogger(Canal.class);

    private final List<Observer<GeneratorAsync>> observers = new ArrayList<>();

    // doit obtenir le bon scheduler
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
    
    private long value;
    
    @Override
    public void addObserver(Observer<GeneratorAsync> o) {
        this.observers.add(o);
    }
    
    @Override
    public void removeObserver(Observer<GeneratorAsync> o) {
        this.observers.remove(o);
    }
    
    @Override
    public void notifyObservers() {
    
    }
    
    @Override
    public List<Observer<GeneratorAsync>> getObservers() {
        return this.observers;
    }
    
    @Override
    public Future<Void> update(Generator g) {
        final int latency = new Random().nextInt(5000) + 500;
    
        this.value = g.getValue();
        
        log.info("UPDATE {} with {}ms latency", this.value,latency);
    
        return executorService.schedule(() -> {
                observers.forEach(o -> o.update(this));
                return null;
            }
        , latency, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Fournit un future d'un long.
     *
     * @return Future de long
     */
    @Override
    public Future<Long> getValue() {
        final int latency = new Random().nextInt(5000) + 500;
    
        log.info("GET {} with {}ms latency", this.value, latency);
    
        return executorService.schedule(() -> this.value, latency, TimeUnit.MILLISECONDS);
    }
}
