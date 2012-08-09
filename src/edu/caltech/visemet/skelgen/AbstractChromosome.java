package edu.caltech.visemet.skelgen;

import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractChromosome implements Chromosome {

    private Gene[] genes;

    public AbstractChromosome(Gene[] genes) {
        this.genes = genes;
    }

    @Override
    public void initialize() {
        for (Gene gene : genes) {
            gene.initialize();
        }
    }

    @Override
    public void randomize(Random random) {
        for (Gene gene : genes) {
            gene.randomize(random);
        }
    }

    @Override
    public Gene getGeneAt(int index) {
        return genes[index];
    }

    @Override
    public void setGeneAt(int index, Gene gene) {
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
