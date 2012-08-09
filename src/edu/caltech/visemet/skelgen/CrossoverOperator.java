package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator {

    Chromosome crossover(Chromosome c1, Chromosome c2);
}
