package edu.caltech.visemet.skim.examples.makechange;

import edu.caltech.visemet.skim.*;
import edu.caltech.visemet.skim.algorithms.SteadyTerminationGeneticAlgorithm;
import edu.caltech.visemet.skim.cache.RandomReplacementCache;
import edu.caltech.visemet.skim.genes.number.IntegerBase;
import edu.caltech.visemet.skim.genes.number.IntegerGene;
import edu.caltech.visemet.skim.operators.crossover.NpointCrossover;
import edu.caltech.visemet.skim.operators.selector.RouletteWheelSelector;
import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class MakeChangeExample<
        T extends Integer,
        S extends NumberBase<T>,
        U extends MappedGene<String, T, S>,
        V extends Chromosome<T, S, U>
> extends AbstractExample<T, S, U, V> {

    private final int target = 19432981;
    // private final int target = 8912;

    private Random random = new Random();

    private List<String> keys = new ArrayList<>();
    private Map<String, Integer> map = new HashMap<>();

    public MakeChangeExample(ExampleConfiguration config) {
        super(config);

        keys.add("penny");
        map.put("penny", 1);

        keys.add("nickel");
        map.put("nickel", 5);

        keys.add("dime");
        map.put("dime", 10);

        keys.add("quarter");
        map.put("quarter", 25);
    }

    @Override
    protected void createEvaluator() {
        /* ExampleConfiguration config = getConfig();
        int populationSize = config.getPopulationSize(); */

        int populationSize = 30;

        setEvaluator(new CachedFitnessEvaluator<>(
                new RandomReplacementCache<V, Double>(populationSize),
                evaluator));

        // setEvaluator(evaluator);
    }

    @Override
    protected void createSelector() {
        setSelector(new RouletteWheelSelector<T, S, U, V>());

        /* ExampleConfiguration config = getConfig();

        int populationSize = config.getPopulationSize(); */

        /* int populationSize = 30;

        setSelector(new TournamentSelector<T, S, U, V>(populationSize / 5)); */
    }

    @Override
    protected void createPopulation() {
        setPopulation(new DefaultPopulation<>(new ArrayList<V>()));

        Population<T, S, U, V> population = getPopulation();
        /* ExampleConfiguration config = getConfig();

        int geneLength = config.getGeneLength();
        int populationSize = config.getPopulationSize(); */

        int geneLength = 4;
        int populationSize = 30;

        for (int count = 0; count < populationSize; count++) {
            List<U> genes = new ArrayList<>();

            IntegerGene<T, S> gene = new IntegerGene<>(geneLength);
            for (String key : keys) {
                IntegerBase base = new IntegerBase(0, target / map.get(key), 0);
                base.randomize(random);

                gene.setBaseAt(keys.indexOf(key), (S) base);
            }

            genes.add((U) new MappedGene<>(keys, gene));
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
                    20000,
                    new GeneticAlgorithmConfiguration.Builder<T, S, U>(1)
                            .setShouldRetainMostFit(true)
                            .setNumCrossoverParents(2)
                            .setCrossoverProbability(0, 0.8)
                            .setMutationProbability(0, 0.09)
                            .setCrossover(0, new NpointCrossover<T, S, U>(1))
                            .setMutator(0, mutator)
                            .build()));
    }

    private FitnessEvaluator<T, S, U, V> evaluator = new FitnessEvaluator<T, S, U, V>() {

        @Override
        public double evaluate(V chromosome) {
            int total = 0;
            int count = 0;

            double maxValue = 0;

            U gene = chromosome.getGeneAt(0);

            for (String key : keys) {
                IntegerBase base = (IntegerBase) gene.getBaseWith(key);

                total += base.getValue() * map.get(key);
                count += base.getValue();

                maxValue += base.getMaxValue() * map.get(key);
            }

            if (total != target) {
                return Math.max(0, maxValue / 2 - Math.pow(Math.abs(target - total), 1));
            }

            return Math.max(0, maxValue - Math.pow(count, 1));
        }
    };

    private MutationOperator<T, S, U> mutator = new MutationOperator<T, S, U>() {

        @Override
        public void mutate(double probability, U gene) {
            int sum = 0;

            for (String key : keys) {
                IntegerBase base = (IntegerBase) gene.getBaseWith(key);

                sum += base.getValue() * map.get(key);
            }

            int geneLength = gene.length();

            for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
                if (random.nextDouble() < probability) {
                    IntegerBase base = (IntegerBase) gene.getBaseAt(baseIndex);

                    if (sum > target) {
                        int diff = sum - target;
                        base.setValue(base.getValue() - diff / map.get(keys.get(baseIndex)));
                    } else if (sum < target) {
                        int diff = sum - target;
                        base.setValue(base.getValue() + diff / map.get(keys.get(baseIndex)));
                    } else {
                        // base.randomize(random);
                        int swapIndex = random.nextInt(geneLength);
                        int temp = base.getValue();
                        base.setValue(gene.getBaseAt(swapIndex).getValue());
                        gene.getBaseAt(swapIndex).setValue((T) (Integer) temp);
                    }
                }
            }
        }
    };

    @Override
    public void execute() {
        // super.execute();

        GeneticAlgorithm<T, S, U, V> algorithm = getAlgorithm();
        Population<T, S, U, V> population = getPopulation();
        SelectionOperator<T, S, U, V> selector = getSelector();
        FitnessEvaluator<T, S, U, V> evaluator = getEvaluator();

        int generationCount = 0;

        while (!algorithm.shouldTerminate()) {
            setPopulation(algorithm.evolve(evaluator, selector, population));
            population = getPopulation();

            generationCount++;
        }

        V chromosome = PopulationStatistics.getMostFitChromosome(
                evaluator, population);

        double fitness = evaluator.evaluate(chromosome);

        System.out.printf(
                "Found solution %s with fitness %.6f after %d generations.\n",
                chromosome, fitness, generationCount);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Example<
                Integer,
                IntegerBase,
                MappedGene<String, Integer, IntegerBase>,
                DefaultChromosome<
                        Integer,
                        IntegerBase,
                        MappedGene<String, Integer, IntegerBase>
                >
        > example = new MakeChangeExample<>(null);

        example.configure();
        example.execute();
    }
}
