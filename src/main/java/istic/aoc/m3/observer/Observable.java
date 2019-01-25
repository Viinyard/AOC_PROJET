package istic.aoc.m3.observer;

import java.util.List;

public interface Observable<T> {

    void addObserver(T o);

    void removeObserver(T o);
    
    void notifyObservers();
    
    List<T> getObservers();
}
