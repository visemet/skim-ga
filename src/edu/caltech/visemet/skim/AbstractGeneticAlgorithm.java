package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGeneticAlgorithm<I extends Individual<I>>
implements GeneticAlgorithm<I> {

    /**
     * Holds the list of selection operators applied by this genetic algorithm.
     */
    private List<SelectionOperator<I>> selectors = new ArrayList<>();

    /**
     * Holds the list of crossover operators applied by this genetic algorithm.
     */
    private List<CrossoverOperator<I>> crossovers = new ArrayList<>();

    /**
     * Holds the list of mutation operators applied by this genetic algorithm.
     */
    private List<MutationOperator<I>> mutators = new ArrayList<>();

    /**
     * Class constructor.
     */
    public AbstractGeneticAlgorithm() { }

    @Override
    public void apply(SelectionOperator<I> selector) {
        selectors.add(selector);
    }

    @Override
    public void apply(CrossoverOperator<I> crossover) {
        crossovers.add(crossover);
    }

    @Override
    public void apply(MutationOperator<I> mutator) {
        mutators.add(mutator);
    }

    @Override
    public Population<I> evolve(Population<I> population, FitnessFunction<I> function) {
        Population<I> nextPopulation = new DefaultPopulation<>();

        for (SelectionOperator<I> selector : selectors) {
            List<I> individuals = selector.select(population, function);
            nextPopulation.expand(individuals);
        }

        for (CrossoverOperator<I> crossover : crossovers) {
            List<I> individuals = crossover.select(population, function);
            nextPopulation.expand(crossover.crossover(individuals));
        }

        for (MutationOperator<I> mutator : mutators) {
            List<I> individuals = mutator.select(population, function);
            nextPopulation.expand(mutator.mutate(individuals));
        }

        return nextPopulation;
    }
}
