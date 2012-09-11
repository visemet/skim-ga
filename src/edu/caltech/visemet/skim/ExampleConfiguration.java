package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class ExampleConfiguration {

    private Builder builder;

    /**
     * Class constructor specifying the configuration builder.
     *
     * @param builder the configuration builder used by this configuration
     */
    public ExampleConfiguration(Builder builder) {
        this.builder = builder;
    }

    /**
     * Returns the gene length of this configuration.
     *
     * @return the gene length of this configuration
     */
    public int getGeneLength() {
        return builder.geneLength;
    }

    /**
     * Returns the population size of this configuration.
     *
     * @return the population size of this configuration
     */
    public int getPopulationSize() {
        return builder.populationSize;
    }

    /**
     * Returns the number of generations of this configuration.
     *
     * @return the number of generations of this configuration
     */
    public int getNumGenerations() {
        return builder.numGenerations;
    }

    /**
     * Returns the crossover probability of this configuration.
     *
     * @return the crossover probability of this configuration
     */
    public double getCrossoverProbability() {
        return builder.crossoverProbability;
    }

    /**
     * Returns the mutation probability of this configuration.
     *
     * @return the mutation probability of this configuration
     */
    public double getMutationProbability() {
        return builder.mutationProbability;
    }

    /**
     * Builder for an {@link ExampleConfiguration}.
     */
    public static class Builder {

        private int geneLength;
        private int populationSize;
        private int numGenerations;
        private double crossoverProbability;
        private double mutationProbability;

        /**
         * Class constructor.
         */
        public Builder() { }

        /**
         * Replaces the gene length of this configuration builder with the
         * specified gene length.
         *
         * @param geneLength the gene length to be stored
         *
         * @return this configuration builder
         */
        public Builder setGeneLength(int geneLength) {
            this.geneLength = geneLength;
            return this;
        }

        /**
         * Replaces the population size of this configuration builder with the
         * specified population size.
         *
         * @param populationSize the population size to be stored
         *
         * @return this configuration builder
         */
        public Builder setPopulationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        /**
         * Replaces the number of generations of this configuration builder with
         * the specified number of generations.
         *
         * @param numGenerations the number of generations to be stored
         *
         * @return this configuration builder
         */
        public Builder setNumGenerations(int numGenerations) {
            this.numGenerations = numGenerations;
            return this;
        }

        /**
         * Replaces the crossover probability of this configuration builder with
         * the specified crossover probability.
         *
         * @param crossoverProbability the crossover probability to be stored
         *
         * @return this configuration builder
         */
        public Builder setCrossoverProbability(double crossoverProbability) {
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        /**
         * Replaces the mutation probability of this configuration builder with
         * the specified mutation probability.
         *
         * @param mutationProbability the mutation probability to be stored
         *
         * @return this configuration builder
         */
        public Builder setMutationProbability(double mutationProbability) {
            this.mutationProbability = mutationProbability;
            return this;
        }

        /**
         * Returns a configuration with properties identical to this
         * configuration builder.
         *
         * @return a configuration with properties identical to this
         * configuration builder
         */
        public ExampleConfiguration build() {
            return new ExampleConfiguration(this);
        }
    }
}
