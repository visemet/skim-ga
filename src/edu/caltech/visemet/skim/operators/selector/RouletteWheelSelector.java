package edu.caltech.visemet.skim.operators.selector;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.Population;
import edu.caltech.visemet.skim.SelectionOperator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * selection operator
 * @param <S> the type of base for genes of chromosomes of this selection
 * operator
 * @param <U> the type of gene for chromosomes of this selection operator
 * @param <V> the type of chromosome for this selection operator
 *
 * @author Max Hirschhorn #visemet
 */
public class RouletteWheelSelector<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements SelectionOperator<T, S, U, V> {

    /**
     * Holds the random number generation of this selection operation.
     */
    private Random random = new Random();

    /**
     * Class constructor.
     */
    public RouletteWheelSelector() { }

    private void normalize(double[] fitnesses) {
        double sum = 0;
        for (double fitness : fitnesses) {
            sum += fitness;
        }

        int length = fitnesses.length;
        for (int index = 0; index < length; index++) {
            fitnesses[index] /= sum;
        }
    }

    @Override
    public List<V> select(
            int count, final FitnessEvaluator<T, S, U, V> evaluator,
            Population<T, S, U, V> population) {

        List<V> chromosomes = population.asList();

        Collections.sort(chromosomes, new Comparator<V>() {

            @Override
            public int compare(V c1, V c2) {
                double fitness1 = evaluator.evaluate(c1);
                double fitness2 = evaluator.evaluate(c2);

                return (int) (fitness1 - fitness2);
            }
        });

        int length = population.size();

        double[] fitnesses = new double[length];
        for (int index = 0; index < length; index++) {
            fitnesses[index] = evaluator.evaluate(chromosomes.get(index));
        }

        normalize(fitnesses);

        double[] probabilities = new double[count];
        for (int index = 0; index < count; index++) {
            probabilities[index] = random.nextDouble();
        }

        Arrays.sort(probabilities);

        double prevTotalFitness = 0;

        int selectedIndex = 0;
        int chromosomeIndex = 0;

        List<V> selected = new ArrayList<>(
                Collections.nCopies(count, (V) null));

        while (selectedIndex < count) {
            double probability = probabilities[selectedIndex];
            double fitness = fitnesses[chromosomeIndex];

            double totalFitness = prevTotalFitness + fitness;
            if (probability < totalFitness) {
                selected.set(selectedIndex, chromosomes.get(chromosomeIndex));
                selectedIndex++;
            } else {
                prevTotalFitness = totalFitness;
                chromosomeIndex++;
            }
        }

        return selected;
    }
}
