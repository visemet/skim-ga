package edu.caltech.visemet.skim.operators.mutator;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of this mutation operator
 * @param <S> the type of base for genes of this mutation operator
 * @param <U> the type of gene for this mutation operator
 *
 * @author Max Hirschhorn #visemet
 */
public class InversionMutator<
        T extends Boolean,
        S extends Base<T>,
        U extends Gene<T, S>
> implements MutationOperator<T, S, U> {

    /**
     * Holds the random number generator used by this mutation operator.
     */
    private Random random = new Random();

    /**
     * Class constructor.
     */
    public InversionMutator() { }

    @Override
    public void mutate(double probability, U gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                S base = gene.getBaseAt(baseIndex);

                Boolean inverse = !base.getValue();
                base.setValue((T) inverse);
            }
        }
    }
}
