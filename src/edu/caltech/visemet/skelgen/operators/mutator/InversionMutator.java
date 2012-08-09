package edu.caltech.visemet.skelgen.operators.mutator;

import edu.caltech.visemet.skelgen.Base;
import edu.caltech.visemet.skelgen.Gene;
import edu.caltech.visemet.skelgen.MutationOperator;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class InversionMutator implements MutationOperator {

    private Random random = new Random();

    @Override
    public void mutate(double probability, Gene gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                Base<Boolean> base = gene.getBaseAt(baseIndex);
                base.setValue(!base.getValue());
            }
        }
    }
}
