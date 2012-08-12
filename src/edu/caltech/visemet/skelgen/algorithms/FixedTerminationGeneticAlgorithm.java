package edu.caltech.visemet.skelgen.algorithms;

import edu.caltech.visemet.skelgen.*;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FixedTerminationGeneticAlgorithm extends AbstractGeneticAlgorithm {

    private int generationCount;

    private int maxGenerationCount;

    public FixedTerminationGeneticAlgorithm(int maxGenerationCount, GeneticAlgorithmConfiguration configuration) {
        super(configuration);
        this.maxGenerationCount = maxGenerationCount;
    }

    @Override
    public Population evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population population) {
        generationCount++;

        return super.evolve(evaluator, selector, population);
    }

    @Override
    public boolean shouldTerminate() {
        return (generationCount >= maxGenerationCount);
    }
}
