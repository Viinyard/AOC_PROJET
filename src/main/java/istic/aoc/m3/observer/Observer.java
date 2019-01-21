package istic.aoc.m3.observer;

import java.util.concurrent.Future;

import istic.aoc.m3.generator.Generator;

public interface Observer<T> {

    Future<T> update(final Generator g);
}
