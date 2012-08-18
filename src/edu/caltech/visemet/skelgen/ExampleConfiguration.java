package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class ExampleConfiguration {

    private Builder builder;

    public ExampleConfiguration(Builder builder) {
        this.builder = builder;
    }

    public int getGeneLength() {
        return builder.geneLength;
    }

    public int getPopulationSize() {
        return builder.populationSize;
    }

    public int getNumGenerations() {
        return builder.numGenerations;
    }

    public double getCrossoverProbability() {
        return builder.crossoverProbability;
    }

    public double getMutationProbability() {
        return builder.mutationProbability;
    }

    public static class Builder {

        private int geneLength;
        private int populationSize;
        private int numGenerations;
        private double crossoverProbability;
        private double mutationProbability;

        public Builder setGeneLength(int geneLength) {
            this.geneLength = geneLength;
            return this;
        }

        public Builder setPopulationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        public Builder setNumGenerations(int numGenerations) {
            this.numGenerations = numGenerations;
            return this;
        }

        public Builder setCrossoverProbability(double crossoverProbability) {
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        public Builder setMutationProbability(double mutationProbability) {
            this.mutationProbability = mutationProbability;
            return this;
        }

        public ExampleConfiguration build() {
            return new ExampleConfiguration(this);
        }
    }
}
