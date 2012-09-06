package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface SelectionOperator<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> {

    List<V> select(int count, FitnessEvaluator<T, S, U, V> evaluator, Population<T, S, U, V> population);
}
