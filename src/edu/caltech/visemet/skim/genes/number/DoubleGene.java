package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.NumberBase;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A gene implementation that holds a collection of {@code double} values.
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public class DoubleGene<
        T extends Double,
        S extends NumberBase<T>
> extends AbstractGene<T, S> {

    /**
     * Class constructor specifying the length.
     *
     * @param length the length of this gene
     */
    public DoubleGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new DoubleBase(0.0));
        }
    }

    @Override
    public DoubleGene<T, S> copy() {
        int length = length();

        DoubleGene<T, S> copy = new DoubleGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (S) getBaseAt(index).copy());
        }

        return copy;
    }
}
