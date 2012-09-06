package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.Base;
import java.util.Arrays;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanGene extends AbstractGene<Boolean, BooleanBase> {

    public BooleanGene(int length) {
        super(Arrays.asList(new BooleanBase[length]));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
           setBaseAt(index, new BooleanBase());
        }
    }

    @Override
    public BooleanGene copy() {
        int length = length();

        BooleanGene copy = new BooleanGene(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, getBaseAt(index).copy());
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Base base : getSequence()) {
            sb.append(base);
        }

        return sb.toString();
    }
}