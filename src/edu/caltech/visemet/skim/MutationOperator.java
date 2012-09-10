package edu.caltech.visemet.skim;

/**
 *
 * @param <T> the type of value for bases of genes of this mutation operator
 * @param <S> the type of base for genes of this mutation operator
 * @param <U> the type of gene for this mutation operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator<T, S extends Base<T>, U extends Gene<T, S>> {

    /**
     * Modifies the values for bases of the specified gene.
     *
     * @param probability the probability that a mutation occurs
     * @param gene the gene used by this mutation operator
     */
    void mutate(double probability, U gene);
}
