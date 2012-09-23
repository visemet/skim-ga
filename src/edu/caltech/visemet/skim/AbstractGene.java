package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A skeletal implementation of the {@code Gene} interface.
 *
 * @param <T> the type of value for bases of this gene
 * @param <S> the type of base for this gene
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGene<T, S extends Base<T>> implements Gene<T, S> {

    private List<S> sequence;

    /**
     * Class constructor specifying the sequence.
     *
     * @param sequence the sequence of this gene
     */
    public AbstractGene(List<S> sequence) {
        validateSequence(sequence);
        this.sequence = sequence;
    }

    /**
     * Checks whether the specified sequence is {@code null}.
     *
     * @param sequence the sequence to be checked
     *
     * @throws IllegalArgumentException if the specified sequence is
     * {@code null}
     */
    private void validateSequence(List<S> sequence) {
        if (sequence == null) {
            throw new IllegalArgumentException("sequence cannot be null");
        }
    }

    /**
     * Checks whether the specified index is negative or greater than or equal
     * to the length of the sequence.
     *
     * @param index the index to be checked
     *
     * @throws IllegalArgumentException if the specified index is negative or
     * greater than or equal to the length of the sequence
     */
    private void validateIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index cannot be negative");
        } else if (index >= length()) {
            throw new IllegalArgumentException(
                    "index cannot be greater than or equals to length");
        }
    }

    /**
     * Checks whether the specified base is {@code null}.
     *
     * @param base the base to be checked
     *
     * @throws IllegalArgumentException if the specified base is {@code null}
     */
    private void validateBase(S base) {
        if (base == null) {
            throw new IllegalArgumentException("base cannot be null");
        }
    }

    @Override
    public void randomize(Random random) {
        for (S base : sequence) {
            base.randomize(random);
        }
    }

    @Override
    public List<S> getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(List<S> sequence)  {
        validateSequence(sequence);
        this.sequence = sequence;
    }


    @Override
    public S getBaseAt(int index) {
        validateIndex(index);
        return sequence.get(index);
    }

    @Override
    public void setBaseAt(int index, S base) {
        validateIndex(index);
        validateBase(base);
        sequence.set(index, base);
    }

    @Override
    public int length() {
        return sequence.size();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.sequence);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final AbstractGene<T, S> other = (AbstractGene<T, S>) obj;

        return Objects.equals(this.sequence, other.sequence);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        sb.append(getBaseAt(0));

        int length = length();
        for (int index = 1; index < length; index++) {
            sb.append(", ").append(getBaseAt(index));
        }

        sb.append("]");

        return sb.toString();
    }
}
