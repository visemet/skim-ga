package edu.caltech.visemet.skim.operators.mutator;

import edu.caltech.visemet.skim.MutationOperator;
import edu.caltech.visemet.skim.genes.BooleanBase;
import edu.caltech.visemet.skim.genes.BooleanGene;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public enum InversionMutator implements MutationOperator<BooleanGene> {

    INSTANCE;

    private Random random = new Random();

    @Override
    public void mutate(double probability, BooleanGene gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                BooleanBase base = gene.getBaseAt(baseIndex);
                base.setValue(!base.getValue());
            }
        }
    }
}
