package edu.caltech.visemet.skelgen;

import edu.caltech.visemet.skelgen.statistics.PopulationStatistics;
import java.util.ArrayList;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractGeneticAlgorithm implements GeneticAlgorithm {

    private GeneticAlgorithmConfiguration configuration;

    public AbstractGeneticAlgorithm(GeneticAlgorithmConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Population evolve(FitnessEvaluator evaluator, SelectionOperator selector, Population population) {
        Population nextPopulation = new DefaultPopulation(new ArrayList<Chromosome>());

        double crossoverProbability = configuration.getCrossoverProbability();
        double mutationProbability = configuration.getMutationProbability();

        CrossoverOperator crossover = configuration.getCrossover();
        MutationOperator mutator = configuration.getMutator();

        int count = population.size();
        if (configuration.shouldRetainMostFit()) {
            nextPopulation.add(PopulationStatistics.getMostFitChromosome(evaluator, population));
            count--;
        }

        Chromosome[] chromosomes = selector.select(count, evaluator, population);
        for (int chromosomeIndex = 0; chromosomeIndex < chromosomes.length; chromosomeIndex++) {
            Chromosome parent = chromosomes[chromosomeIndex];
            Chromosome otherParent = chromosomes[(chromosomeIndex + 1) % chromosomes.length];

            int chromosomeLength = Math.min(parent.length(), otherParent.length());
            Chromosome child = new DefaultChromosome(new Gene[chromosomeLength]);

            for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
                Gene parentGene = parent.getGeneAt(geneIndex);
                Gene otherParentGene = otherParent.getGeneAt(geneIndex);

                Gene childGene = crossover.crossover(crossoverProbability, parentGene, otherParentGene);
                mutator.mutate(mutationProbability, childGene);

                child.setGeneAt(geneIndex, childGene);
            }

            nextPopulation.add(child);
        }

        return nextPopulation;
    }
}
