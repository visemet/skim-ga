package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.Base;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanBase implements Base<Boolean> {

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
    public String toString() {
        return value ? "1" : "0";
    }
}
