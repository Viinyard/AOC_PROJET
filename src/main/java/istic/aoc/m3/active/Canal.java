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
import istic.aoc.m3.observer.Observer;
import istic.aoc.m3.observer.Subject;

/**
 * @author VinYarD
 * created : 17/10/2018, 14:41
 */
public class Canal implements Observer, Subject {

    private final Logger log = LoggerFactory.getLogger(Canal.class);

    private final List<Observer> observerList = new ArrayList<>();

    // doit obtenir le bon scheduler
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    @Override
    public Future<Void> update(Generator g) {

        final int nombreSecondes = new Random().nextInt(8) + 2;

        log.info("Mise à jour du canal qui ajoute {} seconde{} de latence", nombreSecondes, nombreSecondes > 1 ? "s" : "");

        return (Future<Void>) executorService.schedule(() -> {
            observerList.forEach(o -> o.update(g));
        }, nombreSecondes, TimeUnit.SECONDS);
    }

    @Override
    public void attach(Observer o) {
        this.observerList.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.observerList.remove(o);
    }
}
