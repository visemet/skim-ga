package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Example<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    void execute();

    void configure();

    ExampleConfiguration getConfig();

    void setConfig(ExampleConfiguration config);

    FitnessEvaluator<T, S, U, V> getEvaluator();

    void setEvaluator(FitnessEvaluator<T, S, U, V> evaluator);

    SelectionOperator<T, S, U, V> getSelector();

    void setSelector(SelectionOperator<T, S, U, V> selector);

    Population<T, S, U, V> getPopulation();

    void setPopulation(Population<T, S, U, V> population);

    GeneticAlgorithm<T, S, U, V> getAlgorithm();

    void setAlgorithm(GeneticAlgorithm<T, S, U, V> algorithm);
}
