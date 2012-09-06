package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.NumberBase;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IntegerGene<T extends Integer, U extends NumberBase<T>> extends AbstractGene<T, U> {

    public IntegerGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (U) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (U) new IntegerBase());
        }
    }

    @Override
    public IntegerGene<T, U> copy() {
        int length = length();

        IntegerGene<T, U> copy = new IntegerGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (U) getBaseAt(index).copy());
        }

        return copy;
    }
}
