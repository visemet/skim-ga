package edu.caltech.visemet.skelgen.operators.crossover;

import edu.caltech.visemet.skelgen.Base;
import edu.caltech.visemet.skelgen.CrossoverOperator;
import edu.caltech.visemet.skelgen.Gene;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class NpointCrossover implements CrossoverOperator {

    private Random random = new Random();

    private int n;

    public static final NpointCrossover ONE_POINT = new NpointCrossover(1);

    public static final NpointCrossover TWO_POINT = new NpointCrossover(2);

    public NpointCrossover(int n) {
        this.n = n;
    }

    @Override
    public Gene crossover(double probability, Gene parent, Gene... otherParents) {
        Gene child = parent.copy();

        if (random.nextDouble() < probability) {
            for (Gene otherParent : otherParents) {
                int geneLength = child.length();

                int[] points = new int[n + 2];
                for (int pointIndex = 1; pointIndex < points.length - 1; pointIndex++) {
                    points[pointIndex] = random.nextInt(geneLength);
                }

                points[points.length - 1] = geneLength;

                Arrays.sort(points);

                for (int pointIndex = 1; pointIndex < points.length; pointIndex++) {
                    int startPoint = points[pointIndex - 1];
                    int endPoint = points[pointIndex];

                    if (pointIndex % 2 == 0) {
                        for (int baseIndex = startPoint; baseIndex < endPoint; baseIndex++) {
                            Base childBase = child.getBaseAt(baseIndex);
                            Base otherParentBase = otherParent.getBaseAt(baseIndex);

                            childBase.setValue(otherParentBase.getValue());
                        }
                    }
                }
            }
        }

        return child;
    }
}
