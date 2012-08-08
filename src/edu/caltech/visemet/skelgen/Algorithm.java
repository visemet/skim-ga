package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Algorithm {

    Population evolve(Function function, Selector selector, Population population);

    boolean shouldTerminate();
}
