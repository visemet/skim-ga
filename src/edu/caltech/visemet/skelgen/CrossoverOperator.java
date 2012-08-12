package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator {

    Gene crossover(double probability, Gene parent, Gene... otherParents);
}
