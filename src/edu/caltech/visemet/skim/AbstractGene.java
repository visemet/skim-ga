package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGene<T, B extends Base<T>> implements Gene<T, B> {

    private B[] sequence;

    public AbstractGene(B[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public void randomize(Random random) {
        for (B base : sequence) {
            base.randomize(random);
        }
    }

    @Override
    public B[] getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(B[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public B getBaseAt(int index) {
        return sequence[index];
    }

    @Override
    public void setBaseAt(int index, B base) {
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
