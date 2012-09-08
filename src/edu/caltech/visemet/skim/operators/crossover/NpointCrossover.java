package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    private void singleCrossover(int[] points, U child, List<U> otherParents) {
        int otherParentsIndex = 0;

        for (int pointIndex = 1; pointIndex < points.length - 1; pointIndex++) {
            if (otherParentsIndex >= otherParents.size()) {
                otherParentsIndex = 0;
            }

            int startPoint = points[pointIndex];
            int endPoint = points[pointIndex + 1];

            U otherParent = otherParents.get(otherParentsIndex);
            for (int baseIndex = startPoint; baseIndex < endPoint;
                    baseIndex++) {

                S childBase = child.getBaseAt(baseIndex);
                S otherParentBase =
                        otherParent.getBaseAt(baseIndex);

                childBase.setValue(otherParentBase.getValue());
            }

            otherParentsIndex++;
        }
    }

    @Override
    public List<U> crossover(double probability, List<U> parents) {
        List<U> children = new ArrayList<>();

        int numParents = parents.size();
        if (numParents < 1) {
            throw new IllegalArgumentException(
                    "need at least two parents to crossover");
        }

        Map<U, List<U>> map = new LinkedHashMap<>(numParents);
        for (int outerIndex = 0; outerIndex < numParents; outerIndex++) {
            U parent = parents.get(outerIndex);

            List<U> otherParents = new ArrayList<>();
            for (int innerIndex = 0; innerIndex < numParents; innerIndex++) {
                if (outerIndex == innerIndex) {
                    continue;
                }

                U iterParent = parents.get(innerIndex);

                if (parent.length() != iterParent.length()) {
                    throw new IllegalArgumentException(
                            "parents have different gene lengths");
                }

                otherParents.add(iterParent);
            }

            U child = (U) parent.copy();
            children.add(child);
            map.put(child, otherParents);
        }

        if (random.nextDouble() < probability) {
            int geneLength = parents.get(0).length();

            int[] points = new int[n + 2];
            for (int pointIndex = 1; pointIndex < points.length - 1;
                    pointIndex++) {

                points[pointIndex] = random.nextInt(geneLength);
            }

            points[points.length - 1] = geneLength;

            Arrays.sort(points);

            for (U child : map.keySet()) {
                List<U> otherParents = map.get(child);
                singleCrossover(points, child, otherParents);
            }
        }

        return children;
    }
}
