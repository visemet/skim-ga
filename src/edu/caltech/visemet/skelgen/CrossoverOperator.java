package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator {

    void crossover(Chromosome parent1, Chromosome parent2, Chromosome child);
}
