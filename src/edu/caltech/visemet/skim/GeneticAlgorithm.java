package edu.caltech.visemet.skim;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * genetic algorithm
 * @param <S> the type of base for genes of chromosomes of this genetic
 * algorithm
 * @param <U> the type of gene for chromosomes of this genetic algorithm
 * @param <V> the type of chromosome for this genetic algorithm
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    /**
     * Returns a population containing the children chromosomes evolved by this
     * genetic algorithm from the specified parent population using specified
     * selection operator and fitness evaluator.
     *
     * @param evaluator the fitness evaluator used by this genetic algorithm
     * @param selector the selection operator used by this genetic algorithm
     * @param population the population from which the chromosomes are evolved
     *
     * @return a population containing the children chromosomes evolved by this
     * genetic algorithm from the specified parent population using specified
     * selection operator and fitness evaluator
     */
    Population<T, S, U, V> evolve(
            FitnessEvaluator<T, S, U, V> evaluator,
            SelectionOperator<T, S, U, V> selector,
            Population<T, S, U, V> population);

    /**
     * Returns whether or not this genetic algorithm should continue to evolve.
     *
     * @return {@code true} if this genetic algorithm should terminate, and
     * {@code false} otherwise
     */
    boolean shouldTerminate();
}
