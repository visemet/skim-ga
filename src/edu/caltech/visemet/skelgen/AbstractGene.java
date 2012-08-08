package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGene<T> implements Gene<T> {

    private T[] sequence;

    public AbstractGene(T[] sequence) {
        this.sequence = sequence;
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
}
