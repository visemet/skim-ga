package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
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
        this.sequence = sequence;
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
    public void setSequence(List<S> sequence) {
        this.sequence = sequence;
    }

    @Override
    public S getBaseAt(int index) {
        return sequence.get(index);
    }

    @Override
    public void setBaseAt(int index, S base) {
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
