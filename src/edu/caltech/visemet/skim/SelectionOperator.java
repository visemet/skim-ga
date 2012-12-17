package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator {

    /**
     * Returns a selection of individuals from the specified population based
     * on the specified fitness function.
     *
     * @param population the population from which individuals are chosen
     * @param function the fitness function used to choose individuals
     *
     * @return a list of individuals from the specified population
     */
    List<Individual> select(Population population, FitnessFunction function);
}
