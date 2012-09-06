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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class RouletteWheelSelector<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements SelectionOperator<T, S, U, V> {

    private Random random = new Random();

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

        List<V> chromosomes = population.toList();

        final Map<Chromosome, Double> cache = new HashMap<>();

        Collections.sort(chromosomes, new Comparator<V>() {

            @Override
            public int compare(V c1, V c2) {
                if (!cache.containsKey(c1)) {
                    cache.put(c1, evaluator.evaluate(c1));
                }

                if (!cache.containsKey(c2)) {
                    cache.put(c2, evaluator.evaluate(c2));
                }

                double fitness1 = cache.get(c1);
                double fitness2 = cache.get(c2);

                return (int) (fitness1 - fitness2);
            }
        });

        int length = population.size();

        double[] fitnesses = new double[length];
        for (int index = 0; index < length; index++) {
            fitnesses[index] = cache.get(chromosomes.get(index));
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
