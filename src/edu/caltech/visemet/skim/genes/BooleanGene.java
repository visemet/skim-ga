package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.Base;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public class BooleanGene<
        T extends Boolean,
        S extends Base<T>
> extends AbstractGene<T, S> {

    /**
     * Class constructor specifying the length.
     *
     * @param length the length of this gene
     */
    public BooleanGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new BooleanBase(false));
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
