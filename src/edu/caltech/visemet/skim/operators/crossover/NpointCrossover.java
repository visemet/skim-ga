package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
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

    private Random random = new Random();

    private int n;

    public static final NpointCrossover ONE_POINT = new NpointCrossover(1);

    public static final NpointCrossover TWO_POINT = new NpointCrossover(2);

    public NpointCrossover(int n) {
        this.n = n;
    }

    @Override
    public U crossover(double probability, U parent, List<U> otherParents) {
        U child = (U) parent.copy();

        if (random.nextDouble() < probability) {
            for (U otherParent : otherParents) {
                int geneLength = child.length();

                int[] points = new int[n + 2];
                for (int pointIndex = 1; pointIndex < points.length - 1;
                        pointIndex++) {

                    points[pointIndex] = random.nextInt(geneLength);
                }

                points[points.length - 1] = geneLength;

                Arrays.sort(points);

                for (int pointIndex = 1; pointIndex < points.length;
                        pointIndex++) {

                    int startPoint = points[pointIndex - 1];
                    int endPoint = points[pointIndex];

                    if (pointIndex % 2 == 0) {
                        for (int baseIndex = startPoint; baseIndex < endPoint;
                                baseIndex++) {

                            S childBase = child.getBaseAt(baseIndex);
                            S otherParentBase =
                                    otherParent.getBaseAt(baseIndex);

                            childBase.setValue(otherParentBase.getValue());
                        }
                    }
                }
            }
        }

        return child;
    }
}
