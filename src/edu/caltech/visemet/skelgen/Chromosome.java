package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Chromosome {

    void randomize();

    Gene getGeneAt(int index);

    void setGeneAt(int index, Gene gene);

    Chromosome crossover(Chromosome other);

    void mutate(double probability);

    int length();
}
