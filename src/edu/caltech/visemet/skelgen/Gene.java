package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Gene<T> {

    void randomize();

    T[] getSequence();

    void setSequence(T[] sequence);

    T getBaseAt(int index);

    void setBaseAt(int index, T base);

    int length();
}
