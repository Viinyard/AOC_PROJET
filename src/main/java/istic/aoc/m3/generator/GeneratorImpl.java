package istic.aoc.m3.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.Subject;

public class GeneratorImpl implements Generator, Subject, Runnable {

    private static final Logger log = LoggerFactory.getLogger(GeneratorImpl.class);

    private final List<Observer<Void>> observers = new ArrayList<>();

    private long sequence = 0;

    private boolean isRunning = true;

    public GeneratorImpl() {

    }

    private void nextValue() {
        this.sequence = this.sequence + 1;
        log.info("Nouvelle valeur : " + this.sequence);
    }

    @Override
    public Long getValue() {
        return this.sequence;
    }

    @Override
    public void attach(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.observers.remove(o);
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        List<Future<Void>> enAttentes = new ArrayList<>();
        while (this.isRunning) {
            if (enAttentes.stream().allMatch(Future::isDone)) {
                this.nextValue();
                enAttentes = this.observers.stream()
                        .map((it) -> it.update(this))
                        .collect(Collectors.toList());
            }
        }
        this.isRunning = true;
    }
}
