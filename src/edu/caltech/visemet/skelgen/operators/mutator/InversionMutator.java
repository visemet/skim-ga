package edu.caltech.visemet.skelgen.operators.mutator;

import edu.caltech.visemet.skelgen.Base;
import edu.caltech.visemet.skelgen.Chromosome;
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
    public void mutate(double probability, Chromosome chromosome) {
        int geneLength = chromosome.length();
        for (int geneIndex = 0; geneIndex < geneLength; geneIndex++) {
            Gene gene = chromosome.getGeneAt(geneIndex);

            int baseLength = gene.length();
            for (int baseIndex = 0; baseIndex < baseLength; baseIndex++) {
                if (random.nextDouble() < probability) {
                    Base<Boolean> base = gene.getBaseAt(baseIndex);
                    base.setValue(!base.getValue());
                }
            }
        }
    }
}
