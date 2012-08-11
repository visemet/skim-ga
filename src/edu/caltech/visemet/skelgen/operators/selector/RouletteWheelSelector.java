package edu.caltech.visemet.skelgen.operators.selector;

import edu.caltech.visemet.skelgen.Chromosome;
import edu.caltech.visemet.skelgen.FitnessEvaluator;
import edu.caltech.visemet.skelgen.Population;
import edu.caltech.visemet.skelgen.SelectionOperator;
import java.util.Arrays;
import java.util.Comparator;
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

        Arrays.sort(chromosomes, new Comparator<Chromosome>() {

            @Override
            public int compare(Chromosome c1, Chromosome c2) {
                return (int) (evaluator.evaluate(c1) - evaluator.evaluate(c2));
            }
        });

        int length = population.size();

        double[] fitnesses = new double[length];
        for (int index = 0; index < length; index++) {
            fitnesses[index] = evaluator.evaluate(chromosomes[index]);
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
