package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Chromosome<T, S extends Base<T>, U extends Gene<T, S>> {

    void initialize();

    void randomize(Random random);

    U getGeneAt(int index);

    void setGeneAt(int index, U gene);

    int length();
}
