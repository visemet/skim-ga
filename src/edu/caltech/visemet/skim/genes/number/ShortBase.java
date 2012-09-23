package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 * A base implementation that holds a {@code short} value.
 *
 * @author Max Hirschhorn #visemet
 */
public class ShortBase implements NumberBase<Short> {

    /**
     * Defines the precision of a short value for the bases.
     */
    private static final short PRECISION = (short) 1;

    /**
     * Holds the minimum value of this base.
     */
    private short minValue;

    /**
     * Holds the maximum value of this base.
     */
    private short maxValue;

    /**
     * Holds the value of this base.
     */
    private short value;

    /**
     * Class constructor specifying the value. Uses {@code Short.MIN_VALUE} and
     * {@code Short.MAX_VALUE} for the minimum and maximum values respectively.
     *
     * @param value the value of this base
     */
    public ShortBase(short value) {
        this(Short.MIN_VALUE, Short.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public ShortBase(short minValue, short maxValue, short value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Short getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Short minValue) {
        this.minValue = minValue;
    }

    @Override
    public Short getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Short maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue((short) (minValue + random.nextDouble()
                * (maxValue - minValue + PRECISION)));
    }

    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public void setValue(Short value) {
        this.value = (short) Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public ShortBase copy() {
        return new ShortBase(minValue, maxValue, value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final ShortBase other = (ShortBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
