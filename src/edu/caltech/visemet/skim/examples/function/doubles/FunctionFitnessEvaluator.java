package edu.caltech.visemet.skim.examples.function.doubles;

import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.NumberBase;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionFitnessEvaluator<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements FitnessEvaluator<T, S, U, V> {

    private Function function;

    public FunctionFitnessEvaluator(Function function) {
        this.function = function;
    }

    @Override
    public double evaluate(V chromosome) {
        int chromosomeLength = chromosome.length();
        double[] vars = new double[chromosomeLength];

        for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
            U gene = chromosome.getGeneAt(geneIndex);

            S base = gene.getBaseAt(0);

            double value = base.getValue();
            vars[geneIndex] = value;
        }

        return function.evaluate(vars);
    }
}
