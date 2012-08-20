package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm {

    Population evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population population);

    boolean shouldTerminate();
}
