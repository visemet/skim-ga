package edu.caltech.visemet.skim.operators.mutator;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import edu.caltech.visemet.skim.genes.BooleanBase;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class InversionMutator<T extends Boolean, S extends Base<T>, U extends Gene<T, S>> implements MutationOperator<T, S, U> {

    private Random random = new Random();

    @Override
    public void mutate(double probability, U gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                BooleanBase base = (BooleanBase) gene.getBaseAt(baseIndex);
                base.setValue(!base.getValue());
            }
        }
    }
}
