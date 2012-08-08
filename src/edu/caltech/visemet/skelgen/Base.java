package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Base<T> {

    void randomize();

    T getValue();

    void setValue(T value);
}
