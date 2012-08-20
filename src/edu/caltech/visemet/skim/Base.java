package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Base<T> {

    void randomize(Random random);

    T getValue();

    void setValue(T value);

    Base<T> copy();
}
