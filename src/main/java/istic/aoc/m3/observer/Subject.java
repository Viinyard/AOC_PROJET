package istic.aoc.m3.observer;

public interface Subject {

    void attach(Observer o);

    void detach(Observer o);
}
