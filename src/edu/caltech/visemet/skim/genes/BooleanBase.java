package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.Base;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanBase implements Base<Boolean> {

    /**
     * Holds the value of this base.
     */
    private boolean value;

    /**
     * Class constructor specifying the value.
     *
     * @param value the value of this base
     */
    public BooleanBase(boolean value) {
        this.value = value;
    }

    @Override
    public void randomize(Random random) {
        setValue(random.nextBoolean());
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public BooleanBase copy() {
        return new BooleanBase(value);
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

        final BooleanBase other = (BooleanBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }
}
