package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator<T, S extends Base<T>, U extends Gene<T, S>> {

    void mutate(double probability, U gene);
}
