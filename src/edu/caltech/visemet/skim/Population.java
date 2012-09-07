package edu.caltech.visemet.skim;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
public interface Population<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    /**
     * Initializes the value for bases of genes of chromosomes of this
     * population.
     *
     * @see Chromosome#initialize()
     */
    void initialize();

    /**
     * Inserts the specified chromosome into this population.
     *
     * @param chromosome the chromosome to be stored
     *
     * @return @code{true} if this population has changed
     *
     * @see Collection#add(java.lang.Object)
     */
    boolean add(V chromosome);

    /**
     * Returns the size of this population.
     *
     * @return the size of this population
     */
    int size();

    /**
     * Returns an iterator over the chromosomes in this population.
     *
     * @return an iterator over the chromosomes in this population
     */
    Iterator<V> iterator();

    /**
     * Returns a list containing all of the chromosomes in this population.
     *
     * @return a list containing all of the chromosomes in this population
     */
    List<V> toList();
}
