package edu.caltech.visemet.skim;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Max Hirschhorn #visemet
 */
@XStreamAlias("simulation")
public class Simulation<I extends Individual<I>> {

    @XStreamAlias("population-factory")
    private PopulationFactory<I> populationFactory;

    @XStreamAlias("fitness-function")
    private FitnessFunction<I> function;

    @XStreamAlias("genetic-algorithm")
    private GeneticAlgorithm<I> algorithm;

    public Simulation() { }

    public PopulationFactory<I> getPopulationFactory() {
        return populationFactory;
    }

    public void setPopulationFactory(PopulationFactory<I> populationFactory) {
        this.populationFactory = populationFactory;
    }

    public FitnessFunction<I> getFunction() {
        return function;
    }

    public void setFunction(FitnessFunction<I> function) {
        this.function = function;
    }

    public GeneticAlgorithm<I> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(GeneticAlgorithm<I> algorithm) {
        this.algorithm = algorithm;
    }

    public void run() {
        Population<I> population = populationFactory.create();
        populationFactory.expand(population);

        population.initialize();
        while (!algorithm.shouldTerminate()) {
            Population<I> nextPopulation = populationFactory.create();
            algorithm.evolve(population, function, nextPopulation);

            population = nextPopulation;
        }
    }
}
