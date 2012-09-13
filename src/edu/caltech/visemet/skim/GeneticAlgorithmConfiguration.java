package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @param <T> the type of value for bases of genes of this configuration
 * @param <S> the type of base for genes of this configuration
 * @param <U> the type of gene for this configuration
 *
 * @author Max Hirschhorn #visemet
 */
public class GeneticAlgorithmConfiguration<
        T,
        S extends Base<T>,
        U extends Gene<T, S>
> {

    private boolean shouldRetainMostFit;

    private int numCrossoverParents;

    private double[] crossoverProbabilities;

    private double[] mutationProbabilities;

    private List<CrossoverOperator<T, S, U>> crossovers;

    private List<MutationOperator<T, S, U>> mutators;

    /**
     * Class constructor specifying the configuration builder.
     *
     * @param builder the configuration builder used by this configuration
     */
    public GeneticAlgorithmConfiguration(Builder<T, S, U> builder) {
        this.shouldRetainMostFit = builder.shouldRetainMostFit;

        this.numCrossoverParents = builder.numCrossoverParents;

        this.crossoverProbabilities = Arrays.copyOf(
                builder.crossoverProbabilities,
                builder.crossoverProbabilities.length);

        this.mutationProbabilities = Arrays.copyOf(
                builder.mutationProbabilities,
                builder.mutationProbabilities.length);

        this.crossovers = new ArrayList<>(builder.crossovers);

        this.mutators = new ArrayList<>(builder.mutators);
    }

    /**
     * Returns whether or not this configuration should retain the most fit
     * chromosome.
     *
     * @return {@code true} if this configuration should retain the most fit
     * chromosome, and {@code false} otherwise
     */
    public boolean shouldRetainMostFit() {
        return shouldRetainMostFit;
    }

    /**
     * Returns the number of parents used by this configuration for the
     * given crossover operator.
     *
     * @return the number of parents used by this configuration for the
     * given crossover operator
     */
    public int getNumCrossoverParents() {
        return numCrossoverParents;
    }

    /**
     * Returns the crossover probability used by this configuration for the
     * given crossover operation at the specified position.
     *
     * @param index the index of the crossover probability to return
     *
     * @return the crossover probability used by this configuration for
     * given crossover operator at the specified position
     */
    public double getCrossoverProbability(int index) {
        return crossoverProbabilities[index];
    }

    /**
     * Returns the mutation probability used by this configuration for the given
     * mutation operator at the specified position.
     *
     * @param index the index of the mutation probability to return
     *
     * @return the mutation probability used by this configuration for the
     * given mutation operator at the specified position
     */
    public double getMutationProbability(int index) {
        return mutationProbabilities[index];
    }

    /**
     * Returns the crossover operator at the specified position of the this
     * configuration.
     *
     * @param index the index of the crossover operator to return
     *
     * @return the crossover operator at the specified position of the this
     * configuration
     */
    public CrossoverOperator<T, S, U> getCrossover(int index) {
        return crossovers.get(index);
    }

    /**
     * Returns the mutation operator at the specified position of this
     * configuration.
     *
     * @param index the index of the mutation operator to return
     *
     * @return the mutation operator at the specified position of this
     * configuration
     */
    public MutationOperator<T, S, U> getMutator(int index) {
        return mutators.get(index);
    }

    /**
     *
     * @param <T> the type of value for bases of genes of this configuration
     * builder
     * @param <S> the type of base for genes of this configuration builder
     * @param <U> the type of gene for this configuration builder
     */
    public static class Builder<T, S extends Base<T>, U extends Gene<T, S>> {

        private boolean shouldRetainMostFit;

        private int numCrossoverParents;

        private double[] crossoverProbabilities;

        private double[] mutationProbabilities;

        private List<CrossoverOperator<T, S, U>> crossovers;

        private List<MutationOperator<T, S, U>> mutators;

        /**
         * Class constructor specifying the chromosome length.
         *
         * @param length the chromosome length of this configuration
         */
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

        /**
         * Replaces whether or not this configuration should retain the most fit
         * chromosome.
         *
         * @param shouldRetainMostFit the value to be stored for whether or not
         * this configuration should retain the most fit chromosome
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setShouldRetainMostFit(
                boolean shouldRetainMostFit) {

            this.shouldRetainMostFit = shouldRetainMostFit;
            return this;
        }

        /**
         * Replaces the number of parents used by this configuration for the
         * given crossover operator with the specified number of parents.
         *
         * @param numCrossoverParents the value to be stored for the number of
         * parents used by the given crossover operator
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setNumCrossoverParents(
                int numCrossoverParents) {

            this.numCrossoverParents = numCrossoverParents;
            return this;
        }

        /**
         * Replaces the crossover probability used by this configuration for the
         * given crossover operator at the specified position with the specified
         * crossover probability.
         *
         * @param index the index of the crossover probability to replace
         * @param probability the crossover probability to be stored
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setCrossoverProbability(
                int index, double probability) {

            this.crossoverProbabilities[index] = probability;
            return this;
        }

        /**
         * Replaces the mutation probability used by this configuration for the
         * given mutation operator at the specified position with the specified
         * mutation probability.
         *
         * @param index the index of the mutation probability to replace
         * @param probability the mutation probability to be stored
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setMutationProbability(
                int index, double probability) {

            this.mutationProbabilities[index] = probability;
            return this;
        }

        /**
         * Replaces the crossover operator at the specified position of this
         * configuration with the specified crossover operator.
         *
         * @param index the index of the crossover operator to replace
         * @param crossover the crossover operator to be stored
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setCrossover(
                int index, CrossoverOperator<T, S, U> crossover) {

            this.crossovers.set(index, crossover);
            return this;
        }

        /**
         * Replaces the mutation operator at the specified position of this
         * configuration with the specified mutation operator.
         *
         * @param index the index of the mutation operator to replace
         * @param mutator the mutation operator to be stored
         *
         * @return this configuration builder
         */
        public Builder<T, S, U> setMutator(
                int index, MutationOperator<T, S, U> mutator) {

            this.mutators.set(index, mutator);
            return this;
        }

        /**
         * Returns a configuration with properties identical to this
         * configuration builder.
         *
         * @return a configuration with properties identical to this
         * configuration builder
         */
        public GeneticAlgorithmConfiguration<T, S, U> build() {
            return new GeneticAlgorithmConfiguration<>(this);
        }
    }
}
