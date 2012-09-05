package edu.caltech.visemet.skim;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultChromosome<T, S extends Base<T>, U extends Gene<T, S>> implements Chromosome<T, S, U> {

    private Gene<T, S>[] genes;

    public DefaultChromosome(Gene<T, S>[] genes) {
        this.genes = genes;
    }

    @Override
    public void initialize() {
        for (Gene<T, S> gene : genes) {
            gene.initialize();
        }
    }

    @Override
    public void randomize(Random random) {
        for (Gene<T, S> gene : genes) {
            gene.randomize(random);
        }
    }

    @Override
    public Gene<T, S> getGeneAt(int index) {
        return genes[index];
    }

    @Override
    public void setGeneAt(int index, Gene<T, S> gene) {
        genes[index] = gene;
    }

    @Override
    public int length() {
        return genes.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        sb.append(getGeneAt(0));

        int length = length();
        for (int index = 1; index < length; index++) {
            sb.append(", ").append(getGeneAt(index));
        }

        sb.append("]");

        return sb.toString();
    }
}
