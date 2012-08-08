package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Base<T> {

    void randomize(Random random);

    T getValue();

    void setValue(T value);
}
