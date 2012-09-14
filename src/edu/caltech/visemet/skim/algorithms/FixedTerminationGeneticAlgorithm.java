package edu.caltech.visemet.skim.algorithms;

import edu.caltech.visemet.skim.*;

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
public class FixedTerminationGeneticAlgorithm<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> extends AbstractGeneticAlgorithm<T, S, U, V> {

    /**
     * Holds the current generation count of this genetic algorithm.
     */
    private int generationCount;

    /**
     * Holds the maximum generation count of this genetic algorithm.
     */
    private int maxGenerationCount;

    /**
     * Class constructor specifying the maximum generation count and the
     * configuration.
     *
     * @param maxGenerationCount the maximum generation count of this genetic
     * algorithm
     * @param config the configuration used by this genetic algorithm
     */
    public FixedTerminationGeneticAlgorithm(
            int maxGenerationCount,
            GeneticAlgorithmConfiguration<T, S, U> config) {

        super(config);
        this.maxGenerationCount = maxGenerationCount;
    }

    @Override
    public Population<T, S, U, V> evolve(
            FitnessEvaluator<T, S, U, V> evaluator,
            SelectionOperator<T, S, U, V> selector,
            Population<T, S, U, V> population) {

        generationCount++;

        return super.evolve(evaluator, selector, population);
    }

    @Override
    public boolean shouldTerminate() {
        return (generationCount >= maxGenerationCount);
    }
}
