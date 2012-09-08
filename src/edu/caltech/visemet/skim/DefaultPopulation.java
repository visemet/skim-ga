package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultPopulation<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements Population<T, S, U, V> {

    private Collection<V> chromosomes;

    private Random random = new Random();

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
    public List<V> toList() {
        return new ArrayList<>(chromosomes);
    }
}
