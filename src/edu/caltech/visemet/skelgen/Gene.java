package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Gene<T extends Base> {

    void initialize();

    void randomize(Random random);

    T[] getSequence();

    void setSequence(T[] sequence);

    T getBaseAt(int index);

    void setBaseAt(int index, T base);

    int length();
}
