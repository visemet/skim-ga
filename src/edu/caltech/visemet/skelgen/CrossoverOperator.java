package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator {

    void crossover(Gene parent1, Gene parent2, Gene child);
}
