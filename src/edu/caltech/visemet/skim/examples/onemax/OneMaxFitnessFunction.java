package edu.caltech.visemet.skim.examples.onemax;

import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.genes.BooleanBase;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class OneMaxFitnessFunction implements FitnessEvaluator {

    @Override
    public double evaluate(Chromosome chromosome) {
        int count = 0;

        Gene gene = chromosome.getGeneAt(0);

        int length = gene.length();
        for (int index = 0; index < length; index++) {
            BooleanBase base = (BooleanBase) gene.getBaseAt(index);
            if (base.getValue()) {
                count++;
            }
        }

        return count;
    }
}
