package edu.caltech.visemet.skim.examples.onemax;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;

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
public class OneMaxFitnessFunction<
        T extends Boolean,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements FitnessEvaluator<T, S, U, V> {

    /**
     * Class constructor.
     */
    public OneMaxFitnessFunction() { }

    @Override
    public double evaluate(V chromosome) {
        int count = 0;

        U gene = chromosome.getGeneAt(0);

        int length = gene.length();
        for (int index = 0; index < length; index++) {
            S base = gene.getBaseAt(index);
            if (base.getValue()) {
                count++;
            }
        }

        return count;
    }
}
