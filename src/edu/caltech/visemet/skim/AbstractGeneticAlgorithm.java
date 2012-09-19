package edu.caltech.visemet.skim;

import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
public abstract class AbstractGeneticAlgorithm<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements GeneticAlgorithm<T, S, U, V> {

    private GeneticAlgorithmConfiguration<T, S, U> config;

    private Random random = new Random();

    /**
     * Class constructor specifying the configuration.
     *
     * @param config the configuration used by this genetic algorithm
     */
    public AbstractGeneticAlgorithm(
            GeneticAlgorithmConfiguration<T, S, U> config) {

        this.config = config;
    }

    @Override
    public Population<T, S, U, V> evolve(
            FitnessEvaluator<T, S, U, V> evaluator,
            SelectionOperator<T, S, U, V> selector,
            Population<T, S, U, V> population) {

        Population<T, S, U, V> nextPopulation =
                new DefaultPopulation<>(new ArrayList<V>());

        int count = population.size();
        if (config.shouldRetainMostFit()) {
            count--;
        }

        int numParents = config.getNumCrossoverParents();
        int numChildren = 0;

        List<V> chromosomes = selector.select(count, evaluator, population);

        int remaining;
        while ((remaining = count - nextPopulation.size()) > 0) {
            List<V> parents = new ArrayList<>(numParents);
            for (int num = 0; num < numParents; num++) {
                int parentIndex = random.nextInt(count);
                parents.add(chromosomes.get(parentIndex));
            }

            int chromosomeLength = parents.get(0).length();

            List<V> children = new ArrayList<>();

            for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
                List<U> parentGenes = new ArrayList<>();
                for (V parent : parents) {
                    parentGenes.add(parent.getGeneAt(geneIndex));
                }

                double crossoverProbability =
                        config.getCrossoverProbability(geneIndex);

                double mutationProbability =
                        config.getMutationProbability(geneIndex);

                CrossoverOperator<T, S, U> crossover =
                        config.getCrossover(geneIndex);

                MutationOperator<T, S, U> mutator =
                        config.getMutator(geneIndex);

                List<U> childrenGenes = crossover.crossover(
                        crossoverProbability, parentGenes);

                numChildren = childrenGenes.size();

                if (geneIndex == 0) {
                    for (int num = 0; num < numChildren; num++) {
                        List<U> genes = new ArrayList<>(Collections.nCopies(
                                chromosomeLength, (U) null));

                        V child = (V) new DefaultChromosome<>(genes);
                        children.add(child);
                    }
                }

                for (int childIndex = 0; childIndex < numChildren;
                        childIndex++) {

                    U childGene = childrenGenes.get(childIndex);
                    children.get(childIndex).setGeneAt(geneIndex, childGene);

                    mutator.mutate(mutationProbability, childGene);
                }
            }

            int maxChildIndex = Math.min(remaining, numChildren);
            for (int childIndex = 0; childIndex < maxChildIndex; childIndex++) {
                nextPopulation.add(children.get(childIndex));
            }

            numChildren = 0;
        }

        if (config.shouldRetainMostFit()) {
            nextPopulation.add(PopulationStatistics.getMostFitChromosome(
                    evaluator, population));
        }

        return nextPopulation;
    }
}
