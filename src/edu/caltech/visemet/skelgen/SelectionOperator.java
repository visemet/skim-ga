package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator {

    Chromosome[] select(int count, FitnessEvaluator evaluator, Population population);
}
