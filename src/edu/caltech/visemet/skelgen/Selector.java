package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Selector {

    Chromosome[] select(int count, Evaluator evaluator, Population population);
}
