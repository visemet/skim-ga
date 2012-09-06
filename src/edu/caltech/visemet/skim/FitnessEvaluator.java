package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface FitnessEvaluator<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    double evaluate(V chromosome);
}
