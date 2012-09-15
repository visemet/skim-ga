package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FloatBase implements NumberBase<Float> {

    /**
     * Defines the precision of a float value for the bases.
     */
    private static final float PRECISION = 1e-45f;

    /**
     * Holds the minimum value of this base.
     */
    private float minValue;

    /**
     * Holds the maximum value of this base.
     */
    private float maxValue;

    /**
     * Holds the value of this base.
     */
    private float value;

    /**
     * Class constructor specifying the value. Uses {@code -Float.MAX_VALUE} and
     * {@code Float.MAX_VALUE} for the minimum and maximum values respectively.
     *
     * @param value the value of this base
     */
    public FloatBase(float value) {
        this(-Float.MAX_VALUE, Float.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public FloatBase(float minValue, float maxValue, float value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Float getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    @Override
    public Float getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue(minValue + (float) (random.nextDouble()
                * (maxValue - minValue + PRECISION)));
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void setValue(Float value) {
        this.value = Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public FloatBase copy() {
        return new FloatBase(minValue, maxValue, value);
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

        final FloatBase other = (FloatBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
