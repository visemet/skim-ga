package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of this chromosome
 * @param <S> the type of base for genes of this chromosome
 * @param <U> the type of gene for this chromosome
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultChromosome<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> implements Chromosome<T, S, U> {

    private List<U> genes;

    /**
     * Class constructor specifying the genes.
     *
     * @param genes the genes of this chromosome
     */
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
    public int hashCode() {
        return Objects.hashCode(this.genes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final DefaultChromosome<T, S, U> other =
                (DefaultChromosome<T, S, U>) obj;

        return Objects.equals(this.genes, other.genes);
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
