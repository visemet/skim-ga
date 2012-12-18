package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<I extends Individual<I>> {

    /**
     * Replaces the selection operator used by this genetic algorithm with the
     * specified selection operator.
     *
     * @param selector the selection operator to apply
     */
    void apply(SelectionOperator<I> selector);

    /**
     * Replaces the crossover operator used by this genetic algorithm with the
     * specified crossover operator.
     *
     * @param crossover the crossover operator to apply
     */
    void apply(CrossoverOperator<I> crossover);

    /**
     * Replaces the mutation operator used by this genetic algorithm with the
     * specified mutation operator.
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
    void evolve(
            Population<I> population, FitnessFunction<I> function,
            Population<I> nextPopulation);

    /**
     * Returns whether or not this genetic algorithm should continue to evolve.
     *
     * @return {@code true} if this genetic algorithm should terminate, and
     * {@code false} otherwise
     */
    boolean shouldTerminate();
}
