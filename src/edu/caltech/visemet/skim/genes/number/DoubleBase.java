package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 * A base implementation that holds a {@code double} value.
 *
 * @author Max Hirschhorn #visemet
 */
public class DoubleBase implements NumberBase<Double> {

    /**
     * Defines the precision of a double value for the bases.
     */
    private static final double PRECISION = 1e-323;

    /**
     * Holds the minimum value of this base.
     */
    private double minValue;

    /**
     * Holds the maximum value of this base.
     */
    private double maxValue;

    /**
     * Holds the value of this base.
     */
    private double value;

    /**
     * Class constructor specifying the value. Uses {@code -Double.MAX_VALUE}
     * and {@code Double.MAX_VALUE} for the minimum and maximum values
     * respectively.
     *
     * @param value the value of this base
     */
    public DoubleBase(double value) {
        this(-Double.MAX_VALUE, Double.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public DoubleBase(double minValue, double maxValue, double value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Double getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    @Override
    public Double getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue(minValue + random.nextDouble()
                * (maxValue - minValue + PRECISION));
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public DoubleBase copy() {
        return new DoubleBase(minValue, maxValue, value);
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

        final DoubleBase other = (DoubleBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
