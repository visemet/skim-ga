package edu.caltech.visemet.skim;

/**
 *
 * @param <I> the type of individuals in this genetic algorithm
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<I extends Individual<I>> {

    /**
     * Retrieves the selection operator used by this genetic algorithm.
     *
     * @return the selection operator used by this genetic algorithm
     */
    SelectionOperator<I> retrieveSelector();

    /**
     * Replaces the selection operator used by this genetic algorithm with the
     * specified selection operator.
     *
     * @param selector the selection operator to apply
     */
    void apply(SelectionOperator<I> selector);

    /**
     * Retrieves the crossover operator used by this genetic algorithm.
     *
     * @return the crossover operator used by this genetic algorithm
     */
    CrossoverOperator<I> retrieveCrossover();

    /**
     * Replaces the crossover operator used by this genetic algorithm with the
     * specified crossover operator.
     *
     * @param crossover the crossover operator to apply
     */
    void apply(CrossoverOperator<I> crossover);

    /**
     * Retrieves the mutation operator used by this genetic algorithm.
     *
     * @return the mutation operator used by this genetic algorithm
     */
    MutationOperator<I> retrieveMutator();

    /**
     * Replaces the mutation operator used by this genetic algorithm with the
     * specified mutation operator.
     *
     * @param mutator the mutation operator to apply
     */
    void apply(MutationOperator<I> mutator);

    /**
     * Expands the next population evolved by this genetic algorithm from the
     * specified population using the specified fitness function.
     *
     * @param population the population to evolve
     * @param function the fitness function used to evolve the population
     * @param nextPopulation an empty population
     *
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
