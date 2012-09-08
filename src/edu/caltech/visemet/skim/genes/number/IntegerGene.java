package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.NumberBase;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerGene<
        T extends Integer,
        S extends NumberBase<T>
> extends AbstractGene<T, S> {

    public IntegerGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new IntegerBase());
        }
    }

    @Override
    public IntegerGene<T, S> copy() {
        int length = length();

        IntegerGene<T, S> copy = new IntegerGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (S) getBaseAt(index).copy());
        }

        return copy;
    }
}
