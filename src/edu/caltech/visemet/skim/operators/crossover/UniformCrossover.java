package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class UniformCrossover<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    private Random random = new Random();

    public UniformCrossover() { }

    private void shuffle(int[] array) {
        for (int index = array.length; index > 1; index--) {
            int swapIndex = random.nextInt(index);

            int temp = array[index - 1];
            array[index - 1] = array[swapIndex];
            array[swapIndex] = temp;
        }
    }

    @Override
    public List<U> crossover(double probability, List<U> parents) {
        List<U> children = new ArrayList<>();

        int numParents = parents.size();
        if (numParents < 2) {
            throw new IllegalArgumentException(
                    "need at least two parents to crossover");
        }

        for (U parent : parents) {
            U child = (U) parent.copy();
            children.add(child);
        }

        if (random.nextDouble() < probability) {
            int[] parentIndexes = new int[parents.size()];
            for (int index = 0; index < parentIndexes.length; index++) {
                parentIndexes[index] = index;
            }

            int geneLength = parents.get(0).length();

            for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
                shuffle(parentIndexes);

                for (int childIndex = 0; childIndex < children.size();
                        childIndex++) {

                    U child = children.get(childIndex);
                    int parentIndex = parentIndexes[childIndex];

                    if (parentIndex != childIndex) {
                        U parent = parents.get(parentIndex);

                        S childBase = child.getBaseAt(baseIndex);
                        S parentBase = parent.getBaseAt(baseIndex);

                        childBase.setValue(parentBase.getValue());
                    }
                }
            }
        }

        return children;
    }
}
