package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator<T extends Gene> {

    void mutate(double probability, T gene);
}
