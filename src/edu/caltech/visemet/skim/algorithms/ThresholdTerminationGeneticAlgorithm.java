package edu.caltech.visemet.skim.algorithms;

import edu.caltech.visemet.skim.AbstractGeneticAlgorithm;
import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.GeneticAlgorithmConfiguration;
import edu.caltech.visemet.skim.Population;
import edu.caltech.visemet.skim.SelectionOperator;
import edu.caltech.visemet.skim.statistics.PopulationStatistics;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * genetic algorithm
 * @param <S> the type of base for genes of chromosomes of this genetic
 * algorithm
 * @param <U> the type of gene for chromosomes of this genetic algorithm
 * @param <V> the type of chromosome for this genetic algorithm
 *
 * @author Max Hirschhorn #visemet
 */
public class ThresholdTerminationGeneticAlgorithm<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> extends AbstractGeneticAlgorithm<T, S, U, V> {

    /**
     * Holds the minimum fitness of this genetic algorithm.
     */
    private int minFitness;

    /**
     * Holds the fitness evaluator of this genetic algorithm.
     */
    private FitnessEvaluator<T, S, U, V> evaluator = null;

    /**
     * Holds the population of this genetic algorithm.
     */
    private Population<T, S, U, V> population = null;

    /**
     * Class constructor specifying the minimum fitness and the configuration.
     *
     * @param minFitness the minimum fitness of this genetic algorithm
     * @param config the configuration used by this genetic algorithm
     */
    public ThresholdTerminationGeneticAlgorithm(
            int minFitness, GeneticAlgorithmConfiguration<T, S, U> config) {

        super(config);
        this.minFitness = minFitness;
    }

    @Override
    public Population<T, S, U, V> evolve(
            FitnessEvaluator<T, S, U, V> evaluator,
            SelectionOperator<T, S, U, V> selector,
            Population<T, S, U, V> population) {

        this.evaluator = evaluator;
        this.population = population;

        return super.evolve(evaluator, selector, population);
    }

    @Override
    public boolean shouldTerminate() {
        if (evaluator == null || population == null) {
            return false;
        }

        V chromosome = PopulationStatistics.getMostFitChromosome(
                evaluator, population);

        double fitness = evaluator.evaluate(chromosome);

        return (fitness >= minFitness);
    }
}
