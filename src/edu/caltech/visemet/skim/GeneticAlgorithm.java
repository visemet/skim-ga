package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<I extends Individual<I>> {

    /**
     * Returns the next population evolved by this genetic algorithm from the
     * specified population using the specified fitness function.
     *
     * @param population the population to evolve
     * @param function the fitness function used to evolve the population
     *
     * @return a population containing the results of the selection, crossover,
     * and mutation operations
     */
    Population<I> evolve(Population<I> population, FitnessFunction<I> function);

    /**
     * Returns whether or not this genetic algorithm should continue to evolve.
     *
     * @return {@code true} if this genetic algorithm should terminate, and
     * {@code false} otherwise
     */
    boolean shouldTerminate();
}
