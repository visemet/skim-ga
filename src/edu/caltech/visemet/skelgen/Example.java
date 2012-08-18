package edu.caltech.visemet.skelgen;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Example {

    void execute();

    void configure();

    ExampleConfiguration getConfig();

    void setConfig(ExampleConfiguration config);

    FitnessEvaluator getEvaluator();

    void setEvaluator(FitnessEvaluator evaluator);

    SelectionOperator getSelector();

    void setSelector(SelectionOperator selector);

    Population getPopulation();

    void setPopulation(Population population);

    GeneticAlgorithm getAlgorithm();

    void setAlgorithm(GeneticAlgorithm algorithm);
}
