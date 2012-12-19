package edu.caltech.visemet.skim;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.List;

/**
 *
 * @param <I> the type of individuals in this genetic algorithm
 *
 * @author Max Hirschhorn #visemet
 */
@XStreamAlias("genetic-algorithm")
public abstract class AbstractGeneticAlgorithm<I extends Individual<I>>
implements GeneticAlgorithm<I> {

    /**
     * Holds the selection operator applied by this genetic algorithm.
     */
    @XStreamAlias("selection-operator")
    private SelectionOperator<I> selector = null;

    /**
     * Holds the crossover operator applied by this genetic algorithm.
     */
    @XStreamAlias("crossover-operator")
    private CrossoverOperator<I> crossover = null;

    /**
     * Holds the mutation operator applied by this genetic algorithm.
     */
    @XStreamAlias("mutation-operator")
    private MutationOperator<I> mutator = null;

    /**
     * Class constructor.
     */
    public AbstractGeneticAlgorithm() { }

    @Override
    public SelectionOperator<I> retrieveSelector() {
        return selector;
    }

    @Override
    public void apply(final SelectionOperator<I> selector) {
        this.selector = selector;
    }

    @Override
    public CrossoverOperator<I> retrieveCrossover() {
        return crossover;
    }

    @Override
    public void apply(final CrossoverOperator<I> crossover) {
        this.crossover = crossover;
    }

    @Override
    public MutationOperator<I> retrieveMutator() {
        return mutator;
    }

    @Override
    public void apply(final MutationOperator<I> mutator) {
        this.mutator = mutator;
    }

    @Override
    public void evolve(
            final Population<I> population, final FitnessFunction<I> function,
            final Population<I> nextPopulation) {

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
