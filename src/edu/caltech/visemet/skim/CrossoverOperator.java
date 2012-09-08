package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @param <T> the type of value for bases of genes of this crossover operator
 * @param <S> the type of base for genes of this crossover operator
 * @param <U> the type of gene for this crossover operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator<T, S extends Base<T>, U extends Gene<T, S>> {

    /**
     * Returns a list containing the genes yielded from this crossover using the
     * specified parent genes.
     *
     * @param probability the probability that a crossover occurs
     * @param parents the list of genes used in this crossover
     *
     * @return a list containing the genes yielded from this crossover using the
     * specified parent genes
     */
    List<U> crossover(double probability, List<U> parents);
}
