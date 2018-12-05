package istic.aoc.m3.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.Subject;

public class GeneratorImpl implements Generator, Subject, Runnable {

    private final List<Observer<Void>> observers = new ArrayList<>();

    private List<Future<Void>> allObserversRecup = new ArrayList<>();

    private long sequence = 0;

    public GeneratorImpl() {

        // Dès que les futures


/*
        while (true) {
            if (this.allObserversRecup.isEmpty() || this.allObserversRecup.stream().allMatch(Future::isDone)) {
                nextValue();
                allObserversRecup = observers.stream().map(it -> it.update(this)).collect(Collectors.toList());
            }

        }
        */
    }

    private void nextValue() {
        this.sequence = this.sequence + 1;
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

    @Override
    public void run() {
        List<Future<Void>> enAttentes = new ArrayList<>();
        while (true) {
            if (enAttentes.stream().allMatch(Future::isDone)) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.sequence = this.sequence + 1;
                enAttentes = this.observers.stream().map((it) -> it.update(this)).collect(Collectors.toList());
            }
        }
    }
}
