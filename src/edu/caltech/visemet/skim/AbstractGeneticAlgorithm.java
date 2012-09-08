package edu.caltech.visemet.skim;

import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGeneticAlgorithm<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements GeneticAlgorithm<T, S, U, V> {

    private GeneticAlgorithmConfiguration<T, S, U> configuration;

    private Random random = new Random();

    public AbstractGeneticAlgorithm(
            GeneticAlgorithmConfiguration<T, S, U> configuration) {

        this.configuration = configuration;
    }

    @Override
    public Population<T, S, U, V> evolve(
            FitnessEvaluator<T, S, U, V> evaluator,
            SelectionOperator<T, S, U, V> selector,
            Population<T, S, U, V> population) {

        Population<T, S, U, V> nextPopulation =
                new DefaultPopulation<>(new ArrayList<V>());

        int count = population.size();
        if (configuration.shouldRetainMostFit()) {
            count--;
        }

        int numParents = configuration.getNumCrossoverParents();

        List<V> chromosomes = selector.select(count, evaluator, population);

        int remaining;
        while ((remaining = count - nextPopulation.size()) > 0) {
            List<V> parents = new ArrayList<>(numParents);
            for (int num = 0; num < numParents; num++) {
                int parentIndex = random.nextInt(count);
                parents.add(chromosomes.get(parentIndex));
            }

            int chromosomeLength = parents.get(0).length();

            List<V> children = new ArrayList<>(numParents);
            for (int num = 0; num < numParents; num++) {
                V child = (V) new DefaultChromosome<>(new ArrayList<>(
                        Collections.nCopies(chromosomeLength, (U) null)));

                children.add(child);
            }

            for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
                List<U> parentGenes = new ArrayList<>();
                for (V parent : parents) {
                    parentGenes.add(parent.getGeneAt(geneIndex));
                }

                double crossoverProbability =
                        configuration.getCrossoverProbability(geneIndex);

                double mutationProbability =
                        configuration.getMutationProbability(geneIndex);

                CrossoverOperator<T, S, U> crossover =
                        configuration.getCrossover(geneIndex);

                MutationOperator<T, S, U> mutator =
                        configuration.getMutator(geneIndex);

                List<U> childrenGene = crossover.crossover(
                        crossoverProbability, parentGenes);

                for (int index = 0; index < numParents; index++) {
                    U childGene = childrenGene.get(index);
                    mutator.mutate(mutationProbability, childGene);
                    children.get(index).setGeneAt(geneIndex, childGene);
                }
            }

            int maxIndex = Math.min(remaining, numParents);
            for (int index = 0; index < maxIndex; index++) {
                nextPopulation.add(children.get(index));
            }
        }

        if (configuration.shouldRetainMostFit()) {
            nextPopulation.add(PopulationStatistics.getMostFitChromosome(
                    evaluator, population));
        }

        return nextPopulation;
    }
}
