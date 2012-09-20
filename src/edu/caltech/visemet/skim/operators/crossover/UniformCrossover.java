package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @param <T> the type of value for bases of genes of this crossover operator
 * @param <S> the type of base for genes of this crossover operator
 * @param <U> the type of gene for this crossover operator
 *
 * @author Max Hirschhorn #visemet
 */
public class UniformCrossover<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    /**
     * Holds the random number generator of this crossover operator.
     */
    private Random random = new Random();

    /**
     * Class constructor.
     */
    public UniformCrossover() { }

    /**
     * Creates an array of positions of the specified number with values equal
     * to the index.
     *
     * @param numPositions the number of positions to be generated
     *
     * @return an array of positions of the specified number with values equal
     * to the index
     */
    private static int[] generatePositions(int numPositions) {
        int[] positions = new int[numPositions];

        for (int index = 0; index < numPositions; index++) {
            positions[index] = index;
        }

        return positions;
    }

    /**
     * Permutes the elements of the specified array using the specified random
     * number generator.
     *
     * @param array the array to be shuffled
     * @param random the random number generator
     *
     * @see Collections#shuffle(java.util.List, java.util.Random)
     */
    private static void shuffleArray(int[] array, Random random) {
        for (int index = array.length; index > 1; index--) {
            int swapIndex = random.nextInt(index);

            int temp = array[index - 1];
            array[index - 1] = array[swapIndex];
            array[swapIndex] = temp;
        }
    }

    /**
     * Replaces the value of the base at the specified index of all the
     * specified children genes with the value of the corresponding specified
     * parent gene.
     *
     * @param baseIndex the index of the base to replace
     * @param positions an array corresponding to the index of the parent used
     * @param children the genes for which the value of the base are replaced
     * @param parents the genes by which the value of the base are replaced
     */
    private void exchange(
            int baseIndex, int[] positions, List<U> children, List<U> parents) {

        for (int childIndex = 0; childIndex < children.size(); childIndex++) {
            U child = children.get(childIndex);

            int parentIndex = positions[childIndex];
            U parent = parents.get(parentIndex);

            S childBase = child.getBaseAt(baseIndex);
            S parentBase = parent.getBaseAt(baseIndex);

            childBase.setValue(parentBase.getValue());
        }
    }

    @Override
    public List<U> crossover(double probability, List<U> parents) {
        int numParents = parents.size();
        if (numParents < 2) {
            throw new IllegalArgumentException(
                    "need at least two parents to crossover");
        }

        int geneLength = -1;

        List<U> children = new ArrayList<>();

        for (U parent : parents) {
            int parentGeneLength = parent.length();
            if (geneLength == -1) {
                geneLength = parentGeneLength;
            } else if (parentGeneLength != geneLength) {
                throw new IllegalArgumentException(
                        "parents must have same gene length");
            }

            U child = (U) parent.copy();
            children.add(child);
        }

        if (random.nextDouble() < probability) {
            int[] positions = generatePositions(numParents);

            for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
                shuffleArray(positions, random);
                exchange(baseIndex, positions, children, parents);
            }
        }

        return children;
    }
}
