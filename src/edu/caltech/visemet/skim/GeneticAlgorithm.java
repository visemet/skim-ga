package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface GeneticAlgorithm<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> {

    Population<T, S, U, V> evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population<T, S, U, V> population);

    boolean shouldTerminate();
}
