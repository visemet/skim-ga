package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Chromosome {

    void initialize();

    void randomize(Random random);

    Gene getGeneAt(int index);

    void setGeneAt(int index, Gene gene);

    Chromosome crossover(Chromosome other);

    void mutate(double probability);

    int length();
}
