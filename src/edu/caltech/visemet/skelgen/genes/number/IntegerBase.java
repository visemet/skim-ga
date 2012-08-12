package edu.caltech.visemet.skelgen.genes.number;

import edu.caltech.visemet.skelgen.NumberBase;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerBase implements NumberBase<Integer> {

    public static int PRECISION = 1;

    private int minValue;
    private int maxValue;

    private int value;

    public IntegerBase() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerBase(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
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
        setValue(minValue + (int) (random.nextDouble() * (maxValue - minValue + PRECISION)));
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
    public String toString() {
        return String.valueOf(value);
    }
}
