package istic.aoc.m3.active;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.Subject;

/**
 * @author VinYarD
 * created : 17/10/2018, 14:41
 */

public class Canal implements Observer, Subject, Generator {

    private final Logger log = LoggerFactory.getLogger(Canal.class);

    private final List<Observer> observerList = new ArrayList<>();

    private long value;

    // doit obtenir le bon scheduler
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    @Override
    public Future<Void> update(Generator g) {

        final Future futureValue = executorService.schedule(() -> {
            observerList.forEach(o -> o.update(g));
        }, 2, TimeUnit.SECONDS);

        log.info("Canal retourne un Future<Long>...");
        return futureValue;
    }

    @Override
    public void attach(Observer o) {
        this.observerList.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.observerList.remove(o);
    }

    @Override
    public Long getValue() {
        return this.value;
    }
}
