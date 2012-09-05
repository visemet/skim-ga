package edu.caltech.visemet.skim;

import edu.caltech.visemet.skim.operators.crossover.NpointCrossover;
import edu.caltech.visemet.skim.operators.mutator.RandomMutator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class GeneticAlgorithmConfiguration<T, S extends Base<T>, U extends Gene<T, S>> {

    private Builder<T, S, U> builder;

    public GeneticAlgorithmConfiguration(Builder<T, S, U> builder) {
        this.builder = builder;
    }

    public double getCrossoverProbability() {
        return builder.crossoverProbability;
    }

    public double getMutationProbability() {
        return builder.mutationProbability;
    }

    public CrossoverOperator<T, S, U> getCrossover() {
        return builder.crossover;
    }

    public MutationOperator<T, S, U> getMutator() {
        return builder.mutator;
    }

    public boolean shouldRetainMostFit() {
        return builder.shouldRetainMostFit;
    }

    public static class Builder<T, S extends Base<T>, U extends Gene<T, S>> {

        private boolean shouldRetainMostFit;

        private double crossoverProbability;

        private double mutationProbability;

        private CrossoverOperator<T, S, U> crossover;

        private MutationOperator<T, S, U> mutator;

        public Builder() {
            shouldRetainMostFit = true;
            crossoverProbability = 1;
            mutationProbability = 0;
            crossover = new NpointCrossover<>(1);
            mutator = new RandomMutator<>();
        }

        public Builder<T, S, U> setShouldRetainMostFit(boolean shouldRetainMostFit) {
            this.shouldRetainMostFit = shouldRetainMostFit;
            return this;
        }

        public Builder<T, S, U> setCrossoverProbability(double crossoverProbability) {
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        public Builder<T, S, U> setMutationProbability(double mutationProbability) {
            this.mutationProbability = mutationProbability;
            return this;
        }

        public Builder<T, S, U> setCrossover(CrossoverOperator<T, S, U> crossover) {
            this.crossover = crossover;
            return this;
        }

        public Builder<T, S, U> setMutator(MutationOperator<T, S, U> mutator) {
            this.mutator = mutator;
            return this;
        }

        public GeneticAlgorithmConfiguration<T, S, U> build() {
            return new GeneticAlgorithmConfiguration<>(this);
        }
    }
}
