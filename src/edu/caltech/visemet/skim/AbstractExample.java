package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractExample<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> implements Example<T, S, U, V> {

    private FitnessEvaluator evaluator;
    private SelectionOperator selector;
    private Population<T, S, U, V> population;
    private GeneticAlgorithm<T, S, U, V> algorithm;
    private ExampleConfiguration config;

    public AbstractExample(ExampleConfiguration config) {
        this.config = config;
    }

    protected abstract void createEvaluator();

    protected abstract void createSelector();

    protected abstract void createPopulation();

    protected abstract void createAlgorithm();

    @Override
    public void execute() {
        population.initialize();
        while (!algorithm.shouldTerminate()) {
            setPopulation(algorithm.evolve(evaluator, selector, population));
        }
    }

    @Override
    public void configure() {
        createEvaluator();
        createSelector();
        createPopulation();
        createAlgorithm();
    }

    @Override
    public ExampleConfiguration getConfig() {
        return config;
    }

    @Override
    public void setConfig(ExampleConfiguration config) {
        this.config = config;
    }

    @Override
    public FitnessEvaluator getEvaluator() {
        return evaluator;
    }

    @Override
    public void setEvaluator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public SelectionOperator getSelector() {
        return selector;
    }

    @Override
    public void setSelector(SelectionOperator selector) {
        this.selector = selector;
    }

    @Override
    public Population<T, S, U, V> getPopulation() {
        return population;
    }

    @Override
    public void setPopulation(Population<T, S, U, V> population) {
        this.population = population;
    }

    @Override
    public GeneticAlgorithm<T, S, U, V> getAlgorithm() {
        return algorithm;
    }

    @Override
    public void setAlgorithm(GeneticAlgorithm<T, S, U, V> algorithm) {
        this.algorithm = algorithm;
    }
}
