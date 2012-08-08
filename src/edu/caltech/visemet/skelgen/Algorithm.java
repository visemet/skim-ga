package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Algorithm {

    Population evolve(Evaluator evaluator, Selector selector, Population population);

    boolean shouldTerminate();
}
