package edu.caltech.visemet.skim;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @param <I> the type of individuals in this simulation
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

    /**
     * Class constructor.
     */
    public Simulation() { }

    /**
     * Stores the configuration of the specified simulation to the specified
     * output stream.
     *
     * @param <I> the type of individuals in the specified simulation
     * @param simulation the simulation to store
     * @param out the output stream
     */
    public static <I extends Individual<I>> void store(
            Simulation<I> simulation, OutputStream out) {

        XStream xstream = new XStream();

        xstream.alias("simulation", Simulation.class);
        xstream.autodetectAnnotations(true);

        xstream.toXML(simulation, out);
    }

    /**
     * Loads a simulation based on the configuration from the specified input
     * stream.
     *
     * @param <I> the type of individuals in the simulation
     * @param in the input stream
     *
     * @return a simulation configured by the specified input stream
     */
    @SuppressWarnings("unchecked")
    public static <I extends Individual<I>> Simulation<I> load(
            InputStream in) {

        XStream xstream = new XStream();

        xstream.alias("simulation", Simulation.class);
        xstream.autodetectAnnotations(true);

        return (Simulation<I>) xstream.fromXML(in);
    }

    /**
     * Returns the population factory used by this simulation.
     *
     * @return the population factory used by this simulation
     */
    public PopulationFactory<I> getPopulationFactory() {
        return populationFactory;
    }

    /**
     * Replaces the population factory used by this simulation with the
     * specified population factory.
     *
     * @param populationFactory the population factory to use
     */
    public void setPopulationFactory(PopulationFactory<I> populationFactory) {
        this.populationFactory = populationFactory;
    }

    /**
     * Returns the fitness function used by this simulation.
     *
     * @return the fitness function used by this simulation
     */
    public FitnessFunction<I> getFunction() {
        return function;
    }

    /**
     * Replaces the fitness function used by this simulation with the specified
     * fitness function.
     *
     * @param function the fitness function to use
     */
    public void setFunction(FitnessFunction<I> function) {
        this.function = function;
    }

    /**
     * Returns the genetic algorithm used by this simulation.
     *
     * @return the genetic algorithm used by this simulation
     */
    public GeneticAlgorithm<I> getAlgorithm() {
        return algorithm;
    }

    /**
     * Replaces the genetic algorithm used by this simulation with the specified
     * genetic algorithm.
     *
     * @param algorithm the genetic algorithm to use
     */
    public void setAlgorithm(GeneticAlgorithm<I> algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Executes this simulation by initializing the population and evolving it
     * until the genetic algorithm indicates to terminate.
     */
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
