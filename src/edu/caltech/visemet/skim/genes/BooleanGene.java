package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.Base;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanGene<
        T extends Boolean,
        U extends Base<T>
> extends AbstractGene<T, U> {

    public BooleanGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (U) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (U) new BooleanBase());
        }
    }

    @Override
    public BooleanGene<T, U> copy() {
        int length = length();

        BooleanGene<T, U> copy = new BooleanGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (U) getBaseAt(index).copy());
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (U base : getSequence()) {
            sb.append(base);
        }

        return sb.toString();
    }
}
