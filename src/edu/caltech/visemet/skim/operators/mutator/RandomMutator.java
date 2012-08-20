package edu.caltech.visemet.skim.operators.mutator;

import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public enum RandomMutator implements MutationOperator {

    INSTANCE;

    private Random random = new Random();

    @Override
    public void mutate(double probability, Gene gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                gene.getBaseAt(baseIndex).randomize(random);
            }
        }
    }
}
