package edu.caltech.visemet.skim.operators.selector;

import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Population;
import edu.caltech.visemet.skim.SelectionOperator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class RouletteWheelSelector implements SelectionOperator {

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
    public Chromosome[] select(int count, final FitnessEvaluator evaluator, Population population) {
        Chromosome[] chromosomes = population.toArray();

        final Map<Chromosome, Double> cache = new HashMap<>();

        Arrays.sort(chromosomes, new Comparator<Chromosome>() {

            @Override
            public int compare(Chromosome c1, Chromosome c2) {
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
            fitnesses[index] = cache.get(chromosomes[index]);
        }

        normalize(fitnesses);

        Chromosome[] selected = new Chromosome[count];
        for (int selectedIndex = 0; selectedIndex < count; selectedIndex++) {
            double probability = random.nextDouble();

            int chromosomeIndex = 0;
            while (chromosomeIndex < length - 1) {
                if (probability < fitnesses[chromosomeIndex]) {
                    break;
                }

                probability -= fitnesses[chromosomeIndex];

                chromosomeIndex++;
            }

            selected[selectedIndex] = chromosomes[chromosomeIndex];
        }

        return selected;
    }
}
