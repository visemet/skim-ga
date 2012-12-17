package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface FitnessFunction<I extends Individual<I>> {

    /**
     * Returns a quality-measure of the specified individual.
     *
     * @param individual the individual to evaluate
     *
     * @return the fitness value for the specified individual
     */
    double evaluate(I individual);
}
