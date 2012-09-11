package edu.caltech.visemet.skim;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * example
 * @param <S> the type of base for genes of chromosomes of this example
 * @param <U> the type of gene for chromosomes of this example
 * @param <V> the type of chromosome for this example
 *
 * @author Max Hirschhorn #visemet
 */
public interface Example<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    /**
     * Executes this example.
     */
    void execute();

    /**
     * Configures this example.
     */
    void configure();

    /**
     * Returns the configuration of this example.
     *
     * @return the configuration of this example
     */
    ExampleConfiguration getConfig();

    /**
     * Replaces the configuration of this example with the specified
     * configuration.
     *
     * @param config the configuration to be stored
     */
    void setConfig(ExampleConfiguration config);

    /**
     * Returns the fitness evaluator used by this example.
     *
     * @return the fitness evaluator used by this example
     */
    FitnessEvaluator<T, S, U, V> getEvaluator();

    /**
     * Replaces the fitness evaluator used by this example.
     *
     * @param evaluator the fitness evaluator to be stored
     */
    void setEvaluator(FitnessEvaluator<T, S, U, V> evaluator);

    /**
     * Returns the selection operator used by this example.
     *
     * @return the selection operator used by this example
     */
    SelectionOperator<T, S, U, V> getSelector();

    /**
     * Replaces the selection operator used by this example.
     *
     * @param selector the selection operator to be stored
     */
    void setSelector(SelectionOperator<T, S, U, V> selector);

    /**
     * Returns the population used by this example.
     *
     * @return the population used by this example
     */
    Population<T, S, U, V> getPopulation();

    /**
     * Replaces the population used by this example.
     *
     * @param population the population to be stored
     */
    void setPopulation(Population<T, S, U, V> population);

    /**
     * Returns the genetic algorithm used by this example.
     *
     * @return the genetic algorithm used by this example
     */
    GeneticAlgorithm<T, S, U, V> getAlgorithm();

    /**
     * Replaces the genetic algorithm used by this example.
     *
     * @param algorithm the genetic algorithm to be stored
     */
    void setAlgorithm(GeneticAlgorithm<T, S, U, V> algorithm);
}
