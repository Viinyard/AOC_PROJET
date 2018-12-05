package istic.aoc.m3.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.Subject;

public class GeneratorImpl implements Generator, Subject {

    private final List<Observer> observers = new ArrayList<>();

    private long sequence = 0;

    GeneratorImpl() {

        // Dès que les futures

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Future<Void>> enAttentes = new ArrayList<>();
                while(true) {
                    if(enAttentes.stream().allMatch(Future::isDone)) {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sequence = sequence + 1;
                        enAttentes = observers.stream()
                                .map((it) -> it.update(this))
                                .collect(Collectors.toList());
                    }
                }
            }
        }) {

        }

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
}
