package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanBase implements Base<Boolean> {

    private boolean value;

    @Override
    public void randomize(Random random) {
        value = random.nextBoolean();
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
    public String toString() {
        return value ? "1" : "0";
    }
}
