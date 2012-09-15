package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class LongBase implements NumberBase<Long> {

    /**
     * Defines the precision of a long value for the bases.
     */
    private static final long PRECISION = 1L;

    /**
     * Holds the minimum value of this base.
     */
    private long minValue;

    /**
     * Holds the maximum value of this base.
     */
    private long maxValue;

    /**
     * Holds the value of this base.
     */
    private long value;

    /**
     * Class constructor specifying the value. Uses {@code Long.MIN_VALUE} and
     * {@code Long.MAX_VALUE} for the minimum and maximum values respectively.
     *
     * @param value the value of this base
     */
    public LongBase(long value) {
        this(Long.MIN_VALUE, Long.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public LongBase(long minValue, long maxValue, long value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Long getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    @Override
    public Long getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue(minValue + (long) (random.nextDouble()
                * (maxValue - minValue + PRECISION)));
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(Long value) {
        this.value = Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public LongBase copy() {
        return new LongBase(minValue, maxValue, value);
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

        final LongBase other = (LongBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
