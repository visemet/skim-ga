package edu.caltech.visemet.skelgen.genes.number;

import edu.caltech.visemet.skelgen.AbstractGene;
import edu.caltech.visemet.skelgen.Gene;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerGene extends AbstractGene<IntegerBase> {

    public IntegerGene(int length) {
        this(new IntegerBase[length]);
    }

    public IntegerGene(IntegerBase[] sequence) {
        super(sequence);
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
