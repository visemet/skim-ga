package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.NumberBase;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class ByteBase implements NumberBase<Byte> {

    /**
     * Defines the precision of a byte value for the bases.
     */
    private static final byte PRECISION = (byte) 1;

    /**
     * Holds the minimum value of this base.
     */
    private byte minValue;

    /**
     * Holds the maximum value of this base.
     */
    private byte maxValue;

    /**
     * Holds the value of this base.
     */
    private byte value;

    /**
     * Class constructor specifying the value. Uses {@code Byte.MIN_VALUE} and
     * {@code Byte.MAX_VALUE} for the minimum and maximum values respectively.
     *
     * @param value the value of this base
     */
    public ByteBase(byte value) {
        this(Byte.MIN_VALUE, Byte.MAX_VALUE, value);
    }

    /**
     * Class constructor specifying the minimum value, maximum value, and value.
     *
     * @param minValue the minimum value of this base
     * @param maxValue the maximum value of this base
     * @param value the value of this base
     */
    public ByteBase(byte minValue, byte maxValue, byte value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public Byte getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(Byte minValue) {
        this.minValue = minValue;
    }

    @Override
    public Byte getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(Byte maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void randomize(Random random) {
        setValue((byte) (minValue + random.nextDouble()
                * (maxValue - minValue + PRECISION)));
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void setValue(Byte value) {
        this.value = (byte) Math.min(Math.max(minValue, value), maxValue);
    }

    @Override
    public ByteBase copy() {
        return new ByteBase(minValue, maxValue, value);
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

        final ByteBase other = (ByteBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
