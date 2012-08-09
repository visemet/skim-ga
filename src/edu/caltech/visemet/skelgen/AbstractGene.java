package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGene<T extends Base> implements Gene<T> {

    private T[] sequence;

    public AbstractGene(T[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public void randomize(Random random) {
        for (T base : sequence) {
            base.randomize(random);
        }
    }

    @Override
    public T[] getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(T[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public T getBaseAt(int index) {
        return sequence[index];
    }

    @Override
    public void setBaseAt(int index, T base) {
        sequence[index] = base;
    }

    @Override
    public int length() {
        return sequence.length;
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
