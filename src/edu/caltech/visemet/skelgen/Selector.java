package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Selector {

    Chromosome[] select(int count, Function function, Population population);
}
