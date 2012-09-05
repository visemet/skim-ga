package edu.caltech.visemet.skim.algorithms;

import edu.caltech.visemet.skim.*;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FixedTerminationGeneticAlgorithm<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> extends AbstractGeneticAlgorithm<T, S, U, V> {

    private int generationCount;

    private int maxGenerationCount;

    public FixedTerminationGeneticAlgorithm(int maxGenerationCount, GeneticAlgorithmConfiguration<T, S, U> configuration) {
        super(configuration);
        this.maxGenerationCount = maxGenerationCount;
    }

    @Override
    public Population<T, S, U, V> evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population<T, S, U, V> population) {
        generationCount++;

        return super.evolve(evaluator, selector, population);
    }

    @Override
    public boolean shouldTerminate() {
        return (generationCount >= maxGenerationCount);
    }
}
