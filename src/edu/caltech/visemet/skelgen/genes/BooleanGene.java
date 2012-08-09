package edu.caltech.visemet.skelgen.genes;

import edu.caltech.visemet.skelgen.AbstractGene;
import edu.caltech.visemet.skelgen.Base;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanGene extends AbstractGene<BooleanBase> {

    public BooleanGene(int length) {
        super(new BooleanBase[length]);
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
           setBaseAt(index, new BooleanBase());
        }
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
