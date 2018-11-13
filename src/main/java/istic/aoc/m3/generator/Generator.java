package istic.aoc.m3.generator;

/**
 * Générateur synchrone
 */
public interface Generator {

    /**
     * Permet d'obtenir la valeur courante du générateur
     * @return Long jamais null
     */
    Long getValue();
    
    void setValue(long value);
}
