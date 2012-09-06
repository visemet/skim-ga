package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.AbstractGene;
import java.util.Arrays;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerGene extends AbstractGene<Integer, IntegerBase> {

    public IntegerGene(int length) {
        super(Arrays.asList(new IntegerBase[length]));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
           setBaseAt(index, new IntegerBase());
        }
    }

    @Override
    public IntegerGene copy() {
        int length = length();

        IntegerGene copy = new IntegerGene(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, getBaseAt(index).copy());
        }

        return copy;
    }
}
