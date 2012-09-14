package edu.caltech.visemet.skim.operators.mutator;

import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import edu.caltech.visemet.skim.NumberBase;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of this mutation operator
 * @param <S> the type of base for genes of this mutation operator
 * @param <U> the type of gene for this mutation operator
 *
 * @author Max Hirschhorn #visemet
 */
public class BoundaryMutator<
        T extends Number,
        S extends NumberBase<T>,
        U extends Gene<T, S>
> implements MutationOperator<T, S, U> {

    /**
     * Holds the floor probability of this mutation operator.
     */
    private double floorProbability;

    /**
     * Holds the random number generator used by this mutation operator.
     */
    private Random random = new Random();

    /**
     * Class constructor. Uses {@code 0.5} for the floor probability.
     */
    public BoundaryMutator() {
        this(0.5);
    }

    /**
     * Class constructor specifying the floor probability.
     *
     * @param floorProbability the floor probability of this mutation operator
     */
    public BoundaryMutator(double floorProbability) {
        this.floorProbability = floorProbability;
    }

    @Override
    public void mutate(double probability, U gene) {
        int geneLength = gene.length();
        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            if (random.nextDouble() < probability) {
                S base = gene.getBaseAt(baseIndex);

                if (random.nextDouble() < floorProbability) {
                    base.setValue(base.getMinValue());
                } else {
                    base.setValue(base.getMaxValue());
                }
            }
        }
    }
}
