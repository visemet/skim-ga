package edu.caltech.visemet.skim.examples.function.floats;

import edu.caltech.visemet.skim.AbstractExample;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.DefaultChromosome;
import edu.caltech.visemet.skim.DefaultPopulation;
import edu.caltech.visemet.skim.ExampleConfiguration;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.GeneticAlgorithm;
import edu.caltech.visemet.skim.GeneticAlgorithmConfiguration;
import edu.caltech.visemet.skim.NumberBase;
import edu.caltech.visemet.skim.Population;
import edu.caltech.visemet.skim.algorithms.SteadyTerminationGeneticAlgorithm;
import edu.caltech.visemet.skim.genes.number.FloatBase;
import edu.caltech.visemet.skim.genes.number.FloatGene;
import edu.caltech.visemet.skim.operators.crossover.UniformCrossover;
import edu.caltech.visemet.skim.operators.mutator.RandomMutator;
import edu.caltech.visemet.skim.operators.selector.TournamentSelector;
import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class FunctionExample<
        T extends Float,
        S extends NumberBase<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> extends AbstractExample<T, S, U, V> {

    private Function function;

    private Random random = new Random();

    public FunctionExample(Function function, ExampleConfiguration config) {
        super(config);
        this.function = function;
    }

    @Override
    protected void createEvaluator() {
        setEvaluator(new FunctionFitnessEvaluator<T, S, U, V>(function));
    }

    @Override
    protected void createSelector() {
        /* ExampleConfiguration config = getConfig();

        int populationSize = config.getPopulationSize(); */

        int populationSize = 30;

        setSelector(new TournamentSelector<T, S, U, V>(populationSize / 5));
    }

    @Override
    protected void createPopulation() {
        setPopulation(new DefaultPopulation<>(new ArrayList<V>()));

        Population<T, S, U, V> population = getPopulation();
        /* ExampleConfiguration config = getConfig();

        int geneLength = config.getGeneLength();
        int populationSize = config.getPopulationSize(); */

        int geneLength = 1;
        int populationSize = 30;

        for (int count = 0; count < populationSize; count++) {
            List<U> genes = new ArrayList<>(geneLength);

            FloatGene<T, S> gene = new FloatGene<>(geneLength);
            FloatBase base = new FloatBase(-100.0f, 100.0f, 0.0f);
            base.randomize(random);

            gene.setBaseAt(0, (S) base);

            genes.add((U) gene);
            population.add((V) new DefaultChromosome<>(genes));
        }
    }

    @Override
    protected void createAlgorithm() {
        /* ExampleConfiguration config = getConfig();

        int numGenerations = config.getNumGenerations();
        double crossoverProbability = config.getCrossoverProbability();
        double mutationProbability = config.getMutationProbability();

        setAlgorithm(new FixedTerminationGeneticAlgorithm<T, S, U, V>(numGenerations,
                new GeneticAlgorithmConfiguration.Builder<T, S, U>()
                        .setShouldRetainMostFit(true)
                        .setCrossoverProbability(crossoverProbability)
                        .setMutationProbability(mutationProbability)
                        .setCrossover(new NpointCrossover<T, S, U>(1))
                        .setMutator(mutator)
                        .build())); */

        setAlgorithm(
            new SteadyTerminationGeneticAlgorithm<T, S, U, V>(
                    1000,
                    new GeneticAlgorithmConfiguration.Builder<T, S, U>(1)
                            .setShouldRetainMostFit(true)
                            .setNumCrossoverParents(2)
                            .setCrossoverProbability(0, 0.6)
                            .setMutationProbability(0, 0.4)
                            .setCrossover(0, new FunctionCrossoverOperator<T, S, U>(new UniformCrossover<Byte, NumberBase<Byte>, Gene<Byte, NumberBase<Byte>>>()))
                            .setMutator(0, new FunctionMutationOperator<T, S, U>(new RandomMutator<Byte, NumberBase<Byte>, Gene<Byte, NumberBase<Byte>>>()))
                            .build()));
    }

    @Override
    public void execute() {
        GeneticAlgorithm<T, S, U, V> algorithm = getAlgorithm();

        int numGenerations = 10;

        int count = 0;
        while (!algorithm.shouldTerminate()) {
            execute(numGenerations);
            count += numGenerations;
        }

        FitnessEvaluator<T, S, U, V> evaluator = getEvaluator();
        Population<T, S, U, V> population = getPopulation();

        V chromosome = PopulationStatistics.getMostFitChromosome(
                evaluator, population);

        double fitness = evaluator.evaluate(chromosome);

        System.out.printf(
                "Found solution %s with fitness %.6f after %d generations.\n",
                chromosome, fitness, count);
    }
}
