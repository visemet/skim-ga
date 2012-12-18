package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @param <I> the type of individuals in this selection operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator<I extends Individual<I>> {

    /**
     * Returns a selection of individuals from the specified population based
     * on the specified fitness function.
     *
     * @param population the population from which individuals are chosen
     * @param function the fitness function used to choose individuals
     *
     * @return a list of individuals from the specified population
     */
    List<I> select(Population<I> population, FitnessFunction<I> function);
}
