package edu.caltech.visemet.skim.individuals;

import edu.caltech.visemet.skim.Individual;

/**
 *
 * @param <I> the type of this individual
 *
 * @author Max Hirschhorn #visemet
 */
public interface ByteArrayIndividual<I extends ByteArrayIndividual<I>>
extends Individual<I> {

    /**
     * Returns the byte value at the specified index.
     *
     * @param index the index of the byte
     *
     * @return the byte value at the specified index
     */
    byte getByte(int index);

    /**
     * Replaces the byte value at the specified index with the specified byte
     * value.
     *
     * @param index the index of the byte
     * @param value the byte value to assign
     */
    void setByte(int index, byte value);
}
