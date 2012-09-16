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
public abstract class AbstractExample<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements Example<T, S, U, V> {

    private FitnessEvaluator<T, S, U, V> evaluator;

    private SelectionOperator<T, S, U, V> selector;

    private Population<T, S, U, V> population;

    private GeneticAlgorithm<T, S, U, V> algorithm;

    private ExampleConfiguration config;

    /**
     * Class constructor specifying the example configuration.
     *
     * @param config the configuration used by this example
     */
    public AbstractExample(ExampleConfiguration config) {
        this.config = config;
    }

    /**
     * Creates a fitness evaluator for this example using the given
     * configuration.
     */
    protected abstract void createEvaluator();

    /**
     * Creates a selection operator for this example using the given
     * configuration.
     */
    protected abstract void createSelector();

    /**
     * Creates a population for this example using the given configuration.
     */
    protected abstract void createPopulation();

    /**
     * Creates a genetic algorithm for this example using the given
     * configuration.
     */
    protected abstract void createAlgorithm();

    @Override
    public void execute() {
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
    public FitnessEvaluator<T, S, U, V> getEvaluator() {
        return evaluator;
    }

    @Override
    public void setEvaluator(FitnessEvaluator<T, S, U, V> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public SelectionOperator<T, S, U, V> getSelector() {
        return selector;
    }

    @Override
    public void setSelector(SelectionOperator<T, S, U, V> selector) {
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
