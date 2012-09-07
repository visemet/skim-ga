package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @param <T> the type of value for this base
 *
 * @author Max Hirschhorn #visemet
 */
public interface Base<T> {

    /**
     * Randomizes the value of this base.
     *
     * @param random the pseudorandom number generator
     */
    void randomize(Random random);

    /**
     * Returns the value of this base.
     *
     * @return the value of this base
     */
    T getValue();

    /**
     * Replaces the value of this base with the specified value.
     *
     * @param value the value to be stored
     */
    void setValue(T value);

    /**
     * Returns an identical base with the same value as this.
     *
     * @return an identical base with the same value as this.
     */
    Base<T> copy();
}
