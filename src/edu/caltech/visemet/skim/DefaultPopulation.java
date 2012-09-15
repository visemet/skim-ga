package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * population
 * @param <S> the type of base for genes of chromosomes of this population
 * @param <U> the type of gene for chromosomes of this population
 * @param <V> the type of chromosome for this population
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultPopulation<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements Population<T, S, U, V> {

    /**
     * Holds the chromosomes of this population.
     */
    private Collection<V> chromosomes;

    /**
     * Holds the random number generation of this population.
     */
    private Random random = new Random();

    /**
     * Class constructor specifying the chromosomes.
     *
     * @param chromosomes the chromosomes in this population
     */
    public DefaultPopulation(Collection<V> chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public void initialize() {
        for (V chromosome : chromosomes) {
            chromosome.initialize();
            chromosome.randomize(random);
        }
    }

    @Override
    public boolean add(V chromosome) {
        return chromosomes.add(chromosome);
    }

    @Override
    public int size() {
        return chromosomes.size();
    }

    @Override
    public Iterator<V> iterator() {
        return chromosomes.iterator();
    }

    @Override
    public List<V> asList() {
        return new ArrayList<>(chromosomes);
    }
}
