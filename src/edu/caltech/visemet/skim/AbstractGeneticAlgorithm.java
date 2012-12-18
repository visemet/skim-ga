package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGeneticAlgorithm<I extends Individual<I>>
implements GeneticAlgorithm<I> {

    /**
     * Holds the selection operator applied by this genetic algorithm.
     */
    private SelectionOperator<I> selector = null;

    /**
     * Holds the crossover operator applied by this genetic algorithm.
     */
    private CrossoverOperator<I> crossover = null;

    /**
     * Holds the mutation operator applied by this genetic algorithm.
     */
    private MutationOperator<I> mutator = null;

    /**
     * Class constructor.
     */
    public AbstractGeneticAlgorithm() { }

    @Override
    public void apply(SelectionOperator<I> selector) {
        this.selector = selector;
    }

    @Override
    public void apply(CrossoverOperator<I> crossover) {
        this.crossover = crossover;
    }

    @Override
    public void apply(MutationOperator<I> mutator) {
        this.mutator = mutator;
    }

    @Override
    public void evolve(
            Population<I> population, FitnessFunction<I> function,
            Population<I> nextPopulation) {

        if (selector != null) {
            List<I> individuals = selector.select(population, function);
            nextPopulation.expand(individuals);
        }

        if (crossover != null) {
            List<I> individuals = crossover.select(population, function);
            nextPopulation.expand(crossover.crossover(individuals));
        }

        if (mutator != null) {
            List<I> individuals = mutator.select(population, function);
            nextPopulation.expand(mutator.mutate(individuals));
        }
    }
}
