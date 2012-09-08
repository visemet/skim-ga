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
        S extends Base<T>
> extends AbstractGene<T, S> {

    public BooleanGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new BooleanBase());
        }
    }

    @Override
    public BooleanGene<T, S> copy() {
        int length = length();

        BooleanGene<T, S> copy = new BooleanGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (S) getBaseAt(index).copy());
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (S base : getSequence()) {
            sb.append(base);
        }

        return sb.toString();
    }
}
