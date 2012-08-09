package edu.caltech.visemet.skelgen.operators.crossover;

import edu.caltech.visemet.skelgen.Chromosome;
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
    public void crossover(Chromosome parent1, Chromosome parent2, Chromosome child) {
        int geneLength = child.length();

        for (int geneIndex = 0; geneIndex < geneLength; geneIndex++) {
            Gene parentGene1 = parent1.getGeneAt(geneIndex);
            Gene parentGene2 = parent2.getGeneAt(geneIndex);
            Gene childGene = child.getGeneAt(geneIndex);

            int baseLength = childGene.length();

            int[] points = new int[n + 2];
            for (int pointIndex = 1; pointIndex < points.length - 1; pointIndex++) {
                points[pointIndex] = random.nextInt(baseLength);
            }

            points[points.length - 1] = baseLength;

            Arrays.sort(points);

            for (int pointIndex = 1; pointIndex < points.length; pointIndex++) {
                int startPoint = points[pointIndex - 1];
                int endPoint = points[pointIndex];

                if (pointIndex % 2 == 1) {
                    for (int baseIndex = startPoint; baseIndex < endPoint; baseIndex++) {
                        childGene.getBaseAt(baseIndex).setValue(parentGene1.getBaseAt(baseIndex).getValue());
                    }
                } else {
                    for (int baseIndex = startPoint; baseIndex < endPoint; baseIndex++) {
                        childGene.getBaseAt(baseIndex).setValue(parentGene2.getBaseAt(baseIndex).getValue());
                    }
                }
            }
        }
    }
}
