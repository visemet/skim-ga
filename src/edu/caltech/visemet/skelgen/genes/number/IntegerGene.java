package edu.caltech.visemet.skelgen.genes.number;

import edu.caltech.visemet.skelgen.AbstractGene;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerGene extends AbstractGene<IntegerBase> {

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
}
