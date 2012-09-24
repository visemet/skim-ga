package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of this crossover operator
 * @param <S> the type of base for genes of this crossover operator
 * @param <U> the type of gene for this crossover operator
 *
 * @author Max Hirschhorn #visemet
 */
public class NpointCrossover<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    /**
     * Holds the number of points of this crossover operator.
     */
    private final int n;

    /**
     * Holds the random number generator of this crossover operator.
     */
    private Random random = new Random();

    /**
     * Class constructor specifying the number of points.
     *
     * @param n the number of points of this crossover operator
     */
    public NpointCrossover(int n) {
        this.n = n;
    }

    /**
     * Creates an array of positions of the specified number with values up to
     * and including the specified length using the specified random number
     * generator.
     *
     * @param numPositions the number of positions to be generated
     * @param length the inclusive maximum value for the generated positions
     * @param random the random number generator
     *
     * @return an array of positions of the specified number with values up to
     * and including the specified length using the specified random number
     * generator
     */
    private static int[] generatePositions(
            int numPositions, int length, Random random) {

        int[] positions = new int[numPositions];

        for (int index = 0; index < positions.length - 1; index++) {
            positions[index] = random.nextInt(length + 1);
        }

        positions[positions.length - 1] = length;

        Arrays.sort(positions);
        return positions;
    }

    /**
     * Replaces the value of bases of the specified child gene with the value of
     * bases of the specified other parent gene between the specified range.
     *
     * @param startIndex the included starting index of the range
     * @param endIndex the excluded ending index of the range
     * @param child the gene for which the value of bases are replaced
     * @param otherParent the gene by which the value of bases are replaced
     */
    private void exchange(
            int startIndex, int endIndex, U child, U otherParent) {

        for (int baseIndex = startIndex; baseIndex < endIndex; baseIndex++) {
            S childBase = child.getBaseAt(baseIndex);
            S otherParentBase = otherParent.getBaseAt(baseIndex);

            childBase.setValue(otherParentBase.getValue());
        }
    }

    /**
     * Replaces the value of bases of all the specified children genes with
     * the value of bases of the corresponding specified other parent genes
     * between each pair of the specified ranges of positions.
     *
     * @param positions the range of values between which the value of bases are
     * replaced
     * @param children the genes for which the value of bases are replaced
     * @param otherParentsList the genes by which the value of bases are
     * replaced
     */
    private void crossover(
            int[] positions, List<U> children, List<List<U>> otherParentsList) {

        int otherParentsIndex = 0;
        int numChildren = children.size();

        for (int pointIndex = 0; pointIndex < positions.length - 1;
                pointIndex += 2) {

            int startPoint = positions[pointIndex];
            int endPoint = positions[pointIndex + 1];

            for (int childIndex = 0; childIndex < numChildren; childIndex++) {
                U child = children.get(childIndex);
                List<U> otherParents = otherParentsList.get(childIndex);

                if (otherParentsIndex >= otherParents.size()) {
                    otherParentsIndex = 0;
                }

                U otherParent = otherParents.get(otherParentsIndex);
                exchange(startPoint, endPoint, child, otherParent);
            }

            otherParentsIndex++;
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

        List<U> children = new ArrayList<>(numParents);
        List<List<U>> otherParentsList = new ArrayList<>(numParents);

        for (int index = 0; index < numParents; index++) {
            U parent = parents.get(index);

            int parentGeneLength = parent.length();
            if (geneLength == -1) {
                geneLength = parentGeneLength;
            } else if (parentGeneLength != geneLength) {
                throw new IllegalArgumentException(
                        "parents must have same gene length");
            }

            U child = (U) parent.copy();
            children.add(child);

            List<U> otherParents = new ArrayList<>();
            otherParents.addAll(parents.subList(0, index));
            otherParents.addAll(parents.subList(index + 1, numParents));

            otherParentsList.add(otherParents);
        }

        if (random.nextDouble() < probability) {
            int[] positions = generatePositions(n + 1, geneLength, random);
            crossover(positions, children, otherParentsList);
        }

        return children;
    }

    @Override
    public int getNumChildren(int numParents) {
        return numParents;
    }
}
