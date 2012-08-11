package edu.caltech.visemet.skelgen.statistics;

import edu.caltech.visemet.skelgen.Chromosome;
import edu.caltech.visemet.skelgen.FitnessEvaluator;
import edu.caltech.visemet.skelgen.Population;
import java.util.Iterator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class PopulationStatistics {

    public static Chromosome getLeastFitChromosome(FitnessEvaluator evaluator, Population population) {
        Iterator<Chromosome> iterator = population.iterator();

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("population is empty");
        }

        Chromosome leastFitChromosome = iterator.next();
        double minFitness = evaluator.evaluate(leastFitChromosome);

        while (iterator.hasNext()) {
            Chromosome chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            if (fitness < minFitness) {
                leastFitChromosome = chromosome;
                minFitness = fitness;
            }
        }

        return leastFitChromosome;
    }

    public static Chromosome getMostFitChromosome(FitnessEvaluator evaluator, Population population) {
        Iterator<Chromosome> iterator = population.iterator();

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("population is empty");
        }

        Chromosome mostFitChromosome = iterator.next();
        double maxFitness = evaluator.evaluate(mostFitChromosome);

        while (iterator.hasNext()) {
            Chromosome chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            if (fitness > maxFitness) {
                mostFitChromosome = chromosome;
                maxFitness = fitness;
            }
        }

        return mostFitChromosome;
    }

    public static double getAverageFitness(FitnessEvaluator evaluator, Population population) {
        double sum = 0;

        Iterator<Chromosome> iterator = population.iterator();
        while (iterator.hasNext()) {
            Chromosome chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            sum += fitness;
        }

        return sum / population.size();
    }
}
