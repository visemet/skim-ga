package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator {

    Chromosome[] select(int count, FitnessEvaluator evaluator, Population population);
}
