package istic.aoc.m3.generator;

import java.util.concurrent.Future;

/**
 * Observable permettant de notifier qu'un générateur à généré une nouvelle valeur
 */
public interface ObservableGeneratorAsync {

    /**
     * Informe d'une nouvelle valeur
     * @param generator -> le générateur ayant une nouvelle valeur
     * @return Un Future (non null)
     */
    Future<Void> update(final Generator generator);
}
