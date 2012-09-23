package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.AbstractGene;
import edu.caltech.visemet.skim.Base;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A gene implementation that holds a collection of {@code char} values.
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public class CharacterGene<
        T extends Character,
        S extends Base<T>
> extends AbstractGene<T, S> {

    /**
     * Class constructor specifying the length.
     *
     * @param length the length of this gene
     */
    public CharacterGene(int length) {
        super(new ArrayList<>(Collections.nCopies(length, (S) null)));
    }

    @Override
    public void initialize() {
        int length = length();
        for (int index = 0; index < length; index++) {
            setBaseAt(index, (S) new CharacterBase(CharacterBase.DIGITS, '0'));
        }
    }

    @Override
    public CharacterGene<T, S> copy() {
        int length = length();

        CharacterGene<T, S> copy = new CharacterGene<>(length);

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
