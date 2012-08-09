package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm {

    Population evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population population);

    boolean shouldTerminate();
}
