package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * selection operator
 * @param <S> the type of base for genes of chromosomes of this selection
 * operator
 * @param <U> the type of gene for chromosomes of this selection operator
 * @param <V> the type of chromosome for this selection operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    /**
     * Returns a list containing the chromosomes chosen by this selection
     * operator from the specified population using the specified fitness
     * evaluator.
     *
     * @param count the number of chromosomes chosen by this selection operator
     * @param evaluator the fitness evaluator used by this selection operator
     * @param population the population from which the chromosomes are chosen
     *
     * @return a list containing the chromosomes chosen by this selection
     * operator from the specified population using the specified fitness
     * evaluator
     */
    List<V> select(
            int count, FitnessEvaluator<T, S, U, V> evaluator,
            Population<T, S, U, V> population);
}
