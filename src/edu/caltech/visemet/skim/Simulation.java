package edu.caltech.visemet.skim;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    private static Class<?> getClass(final Object obj) {
        return obj != null ? obj.getClass() : null;
    }

    /**
     * Stores the configuration of the specified simulation to the specified
     * output stream.
     *
     * @param <I> the type of individuals in the specified simulation
     * @param simulation the simulation to store
     * @param stream the output stream
     *
     * @throws IOException if an input or output error occurred
     */
    public static <I extends Individual<I>> void store(
            final Simulation<I> simulation, final OutputStream stream)
            throws IOException {

        XStream xstream = new XStream();
        xstream.processAnnotations(Simulation.Loader.class);

        Loader loader = new Loader();
        loader.load(getClass(simulation));
        loader.load(getClass(simulation.getPopulationFactory()));
        loader.load(getClass(simulation.getFitnessFunction()));
        loader.load(getClass(simulation.getGeneticAlgorithm()));
        loader.load(getClass(simulation.getGeneticAlgorithm().retrieveSelector()));
        loader.load(getClass(simulation.getGeneticAlgorithm().retrieveCrossover()));
        loader.load(getClass(simulation.getGeneticAlgorithm().retrieveMutator()));

        for (Class<?> clazz : loader.asList()) {
            xstream.processAnnotations(clazz);
        }

        try (ObjectOutputStream out = xstream.createObjectOutputStream(stream)) {
            out.writeObject(loader);
            out.writeObject(simulation);
        }
    }

    /**
     * Loads a simulation based on the configuration from the specified input
     * stream.
     *
     * @param <I> the type of individuals in the simulation
     * @param stream the input stream
     *
     * @return a simulation configured by the specified input stream
     *
     * @throws IOException if an input or output error occurred
     * @throws ClassNotFoundException if the class was not found
     */
    @SuppressWarnings("unchecked")
    public static <I extends Individual<I>> Simulation<I> load(
            final InputStream stream) throws IOException, ClassNotFoundException {

        XStream xstream = new XStream();
        xstream.processAnnotations(Simulation.Loader.class);

        Simulation<I> simulation;

        try (ObjectInputStream in = xstream.createObjectInputStream(stream)) {
            Loader loader = (Loader) in.readObject();

            for (Class<?> clazz : loader.asList()) {
                xstream.processAnnotations(clazz);
            }

            simulation = (Simulation<I>) in.readObject();
        }

        return simulation;
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
    public void setPopulationFactory(final PopulationFactory<I> populationFactory) {
        this.populationFactory = populationFactory;
    }

    /**
     * Returns the fitness function used by this simulation.
     *
     * @return the fitness function used by this simulation
     */
    public FitnessFunction<I> getFitnessFunction() {
        return function;
    }

    /**
     * Replaces the fitness function used by this simulation with the specified
     * fitness function.
     *
     * @param function the fitness function to use
     */
    public void setFunction(final FitnessFunction<I> function) {
        this.function = function;
    }

    /**
     * Returns the genetic algorithm used by this simulation.
     *
     * @return the genetic algorithm used by this simulation
     */
    public GeneticAlgorithm<I> getGeneticAlgorithm() {
        return algorithm;
    }

    /**
     * Replaces the genetic algorithm used by this simulation with the specified
     * genetic algorithm.
     *
     * @param algorithm the genetic algorithm to use
     */
    public void setAlgorithm(final GeneticAlgorithm<I> algorithm) {
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

    /**
     * Container to properly unmarshal a simulation when annotations are used.
     */
    @XStreamAlias("loader")
    private static class Loader {

        /**
         * Holds the list of classes of this loader.
         */
        @XStreamImplicit(itemFieldName="class")
        private final Set<Class<?>> classes = new LinkedHashSet<>();

        /**
         * Class constructor.
         */
        private Loader() { }

        /**
         * Adds the specified class to the list of classes of this loader.
         *
         * @param clazz the class to load
         */
        public void load(final Class<?> clazz) {
            if (clazz != null) {
                classes.add(clazz);
            }
        }

        /**
         * Returns this loader as a list.
         *
         * @return the list of classes of this loader
         */
        public List<Class<?>> asList() {
            return Collections.<Class<?>>unmodifiableList(
                    new ArrayList<>(classes));
        }
    }
}
