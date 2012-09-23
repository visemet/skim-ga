package edu.caltech.visemet.skim.genes.number;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.NumberBase;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A gene implementation that holds a collection of {@code byte} values.
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public class ByteGene<
        T extends Byte,
        S extends NumberBase<T>
> extends AbstractGene<T, S> {

    /**
     * Class constructor specifying the length.
     *
     * @param length the length of this gene
     */
    public ByteGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new ByteBase((byte) 0));
        }
    }

    @Override
    public ByteGene<T, S> copy() {
        int length = length();

        ByteGene<T, S> copy = new ByteGene<>(length);

        for (int index = 0; index < length; index++) {
            copy.setBaseAt(index, (S) getBaseAt(index).copy());
        }

        return copy;
    }
}
