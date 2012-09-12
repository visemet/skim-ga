package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of this chromosome
 * @param <S> the type of base for genes of this chromosome
 * @param <U> the type of gene for this chromosome
 *
 * @author Max Hirschhorn #visemet
 */
public interface Chromosome<T, S extends Base<T>, U extends Gene<T, S>> {

    /**
     * Initializes the value for bases of genes of this chromosome.
     *
     * @see Gene#initialize()
     */
    void initialize();

    /**
     * Randomizes the value for bases of genes of this chromosome.
     *
     * @param random the pseudorandom number generator
     *
     * @see Gene#randomize(java.util.Random)
     */
    void randomize(Random random);

    /**
     * Returns the gene at the specified position of this chromosome.
     *
     * @param index the index of the gene to return
     *
     * @return the gene at the specified position of this chromosome.
     */
    U getGeneAt(int index);

    /**
     * Replaces the gene at the specified position of this chromosome with the
     * specified gene.
     *
     * @param index the index of the gene to replace
     * @param gene the gene to be stored at the specified position
     */
    void setGeneAt(int index, U gene);

    /**
     * Returns the length of this chromosome.
     *
     * @return the length of this chromosome
     */
    int length();
}
