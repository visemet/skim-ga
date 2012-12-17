package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<I extends Individual<I>> {

    /**
     * Adds the specified selection operator to the list of selection operators
     * applied by this genetic algorithm.
     *
     * @param selector the selection operator to apply
     */
    void apply(SelectionOperator<I> selector);

    /**
     * Adds the specified crossover operator to the list of crossover operators
     * applied by this genetic algorithm.
     *
     * @param crossover the crossover operator to apply
     */
    void apply(CrossoverOperator<I> crossover);

    /**
     * Adds the specified mutation operator to the list of mutation operators
     * applied by this genetic algorithm.
     *
     * @param mutator the mutation operator to apply
     */
    void apply(MutationOperator<I> mutator);

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
