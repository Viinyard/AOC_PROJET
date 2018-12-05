package istic.aoc.m3.generator;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.observer.Observer;

public class ObservableGeneratorAsyncImpl implements Observer {

    private final Logger log = LoggerFactory.getLogger(ObservableGeneratorAsyncImpl.class);

    private Observer c;

    @Override
    public Future<Long> update(Generator g) {
        log.info("update()");
        return c.update(g);
    }
}
