package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public interface Gene<T, S extends Base<T>> {

    /**
     * Initializes the value for bases of this gene.
     */
    void initialize();

    /**
     * Randomizes the value for bases of this gene.
     *
     * @param random the pseudorandom number generator
     *
     * @see Base#randomize(java.util.Random)
     */
    void randomize(Random random);

    /**
     * Returns the sequence of this gene.
     *
     * @return the sequence of this gene.
     */
    List<S> getSequence();

    /**
     * Sets the sequence of this gene.
     *
     * @param sequence the sequence to be stored
     */
    void setSequence(List<S> sequence);

    /**
     * Returns the base at the specified position in the sequence of this gene.
     *
     * @param index the index of the base to return
     *
     * @return the base at the specified position in the sequence of this gene.
     */
    S getBaseAt(int index);

    /**
     * Replaces the base at the specified position in the sequence of this gene
     * with the specified base.
     *
     * @param index the index of the base to replace
     * @param base the base to be stored at specified position
     */
    void setBaseAt(int index, S base);

    /**
     * Returns the length of the sequence of this gene.
     *
     * @return the length of the sequence of this gene
     */
    int length();

    /**
     * Returns an identical gene with the same sequence as this. The sequence is
     * composed of identical bases with the same value.
     *
     * @return an identical gene with the same sequence as this
     *
     * @see Base#copy()
     */
    Gene<T, S> copy();
}
