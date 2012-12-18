package edu.caltech.visemet.skim;

/**
 *
 * @param <I> the type of individuals in this fitness function
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
