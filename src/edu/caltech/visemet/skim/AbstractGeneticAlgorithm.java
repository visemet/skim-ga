package edu.caltech.visemet.skim;

import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGeneticAlgorithm<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> implements GeneticAlgorithm<T, S, U, V> {

    private GeneticAlgorithmConfiguration<T, S, U> configuration;

    public AbstractGeneticAlgorithm(GeneticAlgorithmConfiguration<T, S, U> configuration) {
        this.configuration = configuration;
    }

    @Override
    public Population<T, S, U, V> evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population<T, S, U, V> population) {
        Population<T, S, U, V> nextPopulation = new DefaultPopulation<>(new ArrayList<V>());

        double crossoverProbability = configuration.getCrossoverProbability();
        double mutationProbability = configuration.getMutationProbability();

        CrossoverOperator<T, S, U> crossover = configuration.getCrossover();
        MutationOperator<T, S, U> mutator = configuration.getMutator();

        int count = population.size();
        if (configuration.shouldRetainMostFit()) {
            nextPopulation.add(PopulationStatistics.getMostFitChromosome(evaluator, population));
            count--;
        }

        V[] chromosomes = (V[]) selector.select(count, evaluator, population);
        for (int chromosomeIndex = 0; chromosomeIndex < chromosomes.length; chromosomeIndex++) {
            V parent = chromosomes[chromosomeIndex];
            V otherParent = chromosomes[(chromosomeIndex + 1) % chromosomes.length];

            int chromosomeLength = Math.min(parent.length(), otherParent.length());
            V child = (V) new DefaultChromosome<T, S, U>((U[]) new Gene[chromosomeLength]);

            for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
                U parentGene = (U) parent.getGeneAt(geneIndex);
                U otherParentGene = (U) otherParent.getGeneAt(geneIndex);

                U childGene = crossover.crossover(crossoverProbability, parentGene, Collections.singletonList(otherParentGene));
                mutator.mutate(mutationProbability, childGene);

                child.setGeneAt(geneIndex, childGene);
            }

            nextPopulation.add(child);
        }

        return nextPopulation;
    }
}
