package edu.caltech.visemet.skelgen;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultPopulation implements Population {

    private Collection<Chromosome> chromosomes;

    public DefaultPopulation(Collection<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public void initialize() {
        Random random = new Random();

        for (Chromosome chromosome : chromosomes) {
            chromosome.initialize();
            chromosome.randomize(random);
        }
    }

    @Override
    public boolean add(Chromosome chromosome) {
        return chromosomes.add(chromosome);
    }

    @Override
    public int size() {
        return chromosomes.size();
    }

    @Override
    public Iterator<Chromosome> iterator() {
        return chromosomes.iterator();
    }
}
