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
 * @author Max Hirschhorn #visemet
 */
public class NpointCrossover<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    private final int n;

    private Random random = new Random();

    public NpointCrossover(int n) {
        this.n = n;
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
            int[] points = new int[n + 1];
            for (int pointIndex = 0; pointIndex < points.length - 1;
                    pointIndex++) {

                points[pointIndex] = random.nextInt(geneLength + 1);
            }

            points[points.length - 1] = geneLength;

            Arrays.sort(points);

            int otherParentsIndex = 0;

            for (int pointIndex = 0; pointIndex < points.length - 1;
                    pointIndex += 2) {

                int startPoint = points[pointIndex];
                int endPoint = points[pointIndex + 1];

                for (int index = 0; index < numParents; index++) {
                    U child = children.get(index);
                    List<U> otherParents = otherParentsList.get(index);

                    if (otherParentsIndex >= otherParents.size()) {
                        otherParentsIndex = 0;
                    }

                    U otherParent = otherParents.get(otherParentsIndex);
                    for (int baseIndex = startPoint; baseIndex < endPoint;
                            baseIndex++) {

                        S childBase = child.getBaseAt(baseIndex);
                        S otherParentBase =
                                otherParent.getBaseAt(baseIndex);

                        childBase.setValue(otherParentBase.getValue());
                    }
                }

                otherParentsIndex++;
            }
        }

        return children;
    }
}
