package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator {

    void mutate(double probability, Chromosome chromosome);
}
