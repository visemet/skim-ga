package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class GeneticAlgorithmConfiguration<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> {

    private Builder<T, S, U> builder;

    public GeneticAlgorithmConfiguration(Builder<T, S, U> builder) {
        this.builder = builder;
    }

    public boolean shouldRetainMostFit() {
        return builder.shouldRetainMostFit;
    }

    public int getNumCrossoverParents() {
        return builder.numCrossoverParents;
    }

    public double getCrossoverProbability(int index) {
        return builder.crossoverProbabilities[index];
    }

    public double getMutationProbability(int index) {
        return builder.mutationProbabilities[index];
    }

    public CrossoverOperator<T, S, U> getCrossover(int index) {
        return builder.crossovers.get(index);
    }

    public MutationOperator<T, S, U> getMutator(int index) {
        return builder.mutators.get(index);
    }

    public static class Builder<T, S extends Base<T>, U extends Gene<T, S>> {

        private boolean shouldRetainMostFit;

        private int numCrossoverParents;

        private double[] crossoverProbabilities;

        private double[] mutationProbabilities;

        private List<CrossoverOperator<T, S, U>> crossovers;

        private List<MutationOperator<T, S, U>> mutators;

        public Builder(int length) {
            shouldRetainMostFit = true;

            numCrossoverParents = 2;

            crossoverProbabilities = new double[length];

            mutationProbabilities = new double[length];

            crossovers = new ArrayList<>(Collections.nCopies(
                    length, (CrossoverOperator<T, S, U>) null));

            mutators = new ArrayList<>(Collections.nCopies(
                    length, (MutationOperator<T, S, U>) null));
        }

        public Builder<T, S, U> setShouldRetainMostFit(
                boolean shouldRetainMostFit) {

            this.shouldRetainMostFit = shouldRetainMostFit;
            return this;
        }

        public Builder<T, S, U> setNumCrossoverParents(
                int numCrossoverParents) {

            this.numCrossoverParents = numCrossoverParents;
            return this;
        }

        public Builder<T, S, U> setCrossoverProbability(
                int index, double probability) {

            this.crossoverProbabilities[index] = probability;
            return this;
        }

        public Builder<T, S, U> setMutationProbability(
                int index, double probability) {

            this.mutationProbabilities[index] = probability;
            return this;
        }

        public Builder<T, S, U> setCrossover(
                int index, CrossoverOperator<T, S, U> crossover) {

            this.crossovers.set(index, crossover);
            return this;
        }

        public Builder<T, S, U> setMutator(
                int index, MutationOperator<T, S, U> mutator) {

            this.mutators.set(index, mutator);
            return this;
        }

        public GeneticAlgorithmConfiguration<T, S, U> build() {
            return new GeneticAlgorithmConfiguration<>(this);
        }
    }
}
