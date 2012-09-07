package edu.caltech.visemet.skim;

/**
 *
 * @param <T> the type of value for this base
 *
 * @author Max Hirschhorn #visemet
 */
public interface NumberBase<T extends Number> extends Base<T> {

    @Override
    NumberBase<T> copy();

    /**
     * Returns the minimum value accepted by this base.
     *
     * @return the minimum value accepted by this base
     */
    T getMinValue();

    /**
     * Replaces the minimum value of this base with the specified value.
     *
     * @param minValue the value to be stored
     */
    void setMinValue(T minValue);

    /**
     * Returns the maximum value accepted by this base.
     *
     * @return the maximum value accepted by this base
     */
    T getMaxValue();

    /**
     * Replaces the maximum value of this base with the specified value.
     *
     * @param maxValue the value to be store
     */
    void setMaxValue(T maxValue);
}
