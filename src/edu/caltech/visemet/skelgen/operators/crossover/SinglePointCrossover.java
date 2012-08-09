package edu.caltech.visemet.skelgen.operators.crossover;

import edu.caltech.visemet.skelgen.Chromosome;
import edu.caltech.visemet.skelgen.CrossoverOperator;
import edu.caltech.visemet.skelgen.Gene;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class SinglePointCrossover implements CrossoverOperator {

    private Random random = new Random();

    @Override
    public void crossover(Chromosome parent1, Chromosome parent2, Chromosome child) {
        int geneLength = child.length();

        for (int geneIndex = 0; geneIndex < geneLength; geneIndex++) {
            Gene parentGene1 = parent1.getGeneAt(geneIndex);
            Gene parentGene2 = parent2.getGeneAt(geneIndex);
            Gene childGene = child.getGeneAt(geneIndex);

            int baseLength = childGene.length();
            int point = random.nextInt(baseLength);

            for (int baseIndex = 0; baseIndex < point; baseIndex++) {
                childGene.getBaseAt(baseIndex).setValue(parentGene1.getBaseAt(baseIndex).getValue());
            }

            for (int baseIndex = point; baseIndex < baseLength; baseIndex++) {
                childGene.getBaseAt(baseIndex).setValue(parentGene2.getBaseAt(baseIndex).getValue());
            }
        }
    }

}
