package edu.caltech.visemet.skelgen;

import edu.caltech.visemet.skelgen.operators.crossover.NpointCrossover;
import edu.caltech.visemet.skelgen.operators.mutator.RandomMutator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class GeneticAlgorithmConfiguration {

    private Builder builder;

    public GeneticAlgorithmConfiguration(Builder builder) {
        this.builder = builder;
    }

    public double getCrossoverProbability() {
        return builder.crossoverProbability;
    }

    public double getMutationProbability() {
        return builder.mutationProbability;
    }

    public CrossoverOperator getCrossover() {
        return builder.crossover;
    }

    public MutationOperator getMutator() {
        return builder.mutator;
    }

    public static class Builder {

        private double crossoverProbability;

        private double mutationProbability;

        private CrossoverOperator crossover;

        private MutationOperator mutator;

        public Builder() {
            crossoverProbability = 1;
            mutationProbability = 0;
            crossover = NpointCrossover.ONE_POINT;
            mutator = new RandomMutator();
        }

        public Builder setCrossoverProbability(double crossoverProbability) {
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        public Builder setMutationProbability(double mutationProbability) {
            this.mutationProbability = mutationProbability;
            return this;
        }

        public Builder setCrossover(CrossoverOperator crossover) {
            this.crossover = crossover;
            return this;
        }

        public Builder setMutator(MutationOperator mutator) {
            this.mutator = mutator;
            return this;
        }

        public GeneticAlgorithmConfiguration build() {
            return new GeneticAlgorithmConfiguration(this);
        }
    }
}
