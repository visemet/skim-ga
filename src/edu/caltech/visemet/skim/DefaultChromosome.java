package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultChromosome<T, S extends Base<T>, U extends Gene<T, S>> implements Chromosome<T, S, U> {

    private List<U> genes;

    public DefaultChromosome(List<U> genes) {
        this.genes = genes;
    }

    @Override
    public void initialize() {
        for (U gene : genes) {
            gene.initialize();
        }
    }

    @Override
    public void randomize(Random random) {
        for (U gene : genes) {
            gene.randomize(random);
        }
    }

    @Override
    public U getGeneAt(int index) {
        return genes.get(index);
    }

    @Override
    public void setGeneAt(int index, U gene) {
        genes.set(index, gene);
    }

    @Override
    public int length() {
        return genes.size();
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