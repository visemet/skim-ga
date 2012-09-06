package edu.caltech.visemet.skim.statistics;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.Population;
import java.util.Iterator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class PopulationStatistics {

    public static <T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> V getLeastFitChromosome(FitnessEvaluator<T, S, U, V> evaluator, Population<T, S, U, V> population) {
        Iterator<V> iterator = population.iterator();

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("population is empty");
        }

        V leastFitChromosome = iterator.next();
        double minFitness = evaluator.evaluate(leastFitChromosome);

        while (iterator.hasNext()) {
            V chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            if (fitness < minFitness) {
                leastFitChromosome = chromosome;
                minFitness = fitness;
            }
        }

        return leastFitChromosome;
    }

    public static <T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> V getMostFitChromosome(FitnessEvaluator<T, S, U, V> evaluator, Population<T, S, U, V> population) {
        Iterator<V> iterator = population.iterator();

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("population is empty");
        }

        V mostFitChromosome = iterator.next();
        double maxFitness = evaluator.evaluate(mostFitChromosome);

        while (iterator.hasNext()) {
            V chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            if (fitness > maxFitness) {
                mostFitChromosome = chromosome;
                maxFitness = fitness;
            }
        }

        return mostFitChromosome;
    }

    public static <T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> double getAverageFitness(FitnessEvaluator<T, S, U, V> evaluator, Population<T, S, U, V> population) {
        double sum = 0;

        Iterator<V> iterator = population.iterator();
        while (iterator.hasNext()) {
            V chromosome = iterator.next();
            double fitness = evaluator.evaluate(chromosome);

            sum += fitness;
        }

        return sum / population.size();
    }
}
