package edu.caltech.visemet.skim;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * fitness evaluator
 * @param <S> the type of base for genes of chromosomes of this fitness
 * evaluator
 * @param <U> the type of gene for chromosomes of this fitness evaluator
 * @param <V> the type of chromosome for this fitness evaluator
 *
 * @author Max Hirschhorn #visemet
 */
public interface FitnessEvaluator<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    /**
     * Returns a number representing the optimality of the specified chromosome.
     *
     * @param chromosome the chromosome to be evaluated
     *
     * @return a number representing the optimality of the specified chromosome
     */
    double evaluate(V chromosome);
}
