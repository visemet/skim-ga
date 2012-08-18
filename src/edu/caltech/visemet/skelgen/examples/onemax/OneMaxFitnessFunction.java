package edu.caltech.visemet.skelgen.examples.onemax;

import edu.caltech.visemet.skelgen.Chromosome;
import edu.caltech.visemet.skelgen.FitnessEvaluator;
import edu.caltech.visemet.skelgen.Gene;
import edu.caltech.visemet.skelgen.genes.BooleanBase;

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
