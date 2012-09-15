package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerBase implements NumberBase<Integer> {

    /**
     * Defines the precision of an integer value for the bases.
     */
    private static final int PRECISION = 1;

    /**
     * Holds the minimum value of this base.
     */
    private int minValue;

    /**
     * Holds the maximum value of this base.
     */
    private int maxValue;

    /**
     * Holds the value of this base.
     */
    private int value;

    /**
     * Class constructor specifying the value. Uses {@code Integer.MIN_VALUE}
     * and {@code Integer.MAX_VALUE} for the minimum and maximum values
     * respectively.
     *
     * @param value the value of this base
     */
    public IntegerBase(int value) {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public IntegerBase(int minValue, int maxValue, int value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Integer getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    @Override
    public Integer getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue(minValue + (int) (random.nextDouble()
                * (maxValue - minValue + PRECISION)));
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public IntegerBase copy() {
        return new IntegerBase(minValue, maxValue, value);
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

        final IntegerBase other = (IntegerBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
