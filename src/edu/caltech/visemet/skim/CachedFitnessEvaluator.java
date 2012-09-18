package edu.caltech.visemet.skim;

/**
 * @param <T> the type of value for bases of genes of chromosomes of this
 * cached fitness evaluator
 * @param <S> the type of base for genes of chromosomes of this cached fitness
 * evaluator
 * @param <U> the type of gene for chromosomes of this cached fitness evaluator
 * @param <V> the type of chromosome for this cached fitness evaluator
 *
 * @author Max Hirschhorn #visemet
 */
public class CachedFitnessEvaluator<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements FitnessEvaluator<T, S, U, V> {

    /**
     * Holds the cache of this cached fitness evaluator.
     */
    private Cache<V, Double> cache;

    /**
     * Holds the fitness evaluator of this cached fitness evaluator.
     */
    private FitnessEvaluator<T, S, U, V> evaulator;

    /**
     * Class constructor specifying the cache and fitness evaluator.
     *
     * @param cache the cache of this cached fitness evaluator
     * @param evaulator the fitness evaluator wrapped by this cached fitness
     * evaluator
     */
    public CachedFitnessEvaluator(
            Cache<V, Double> cache, FitnessEvaluator<T, S, U, V> evaulator) {

        this.cache = cache;
        this.evaulator = evaulator;
    }

    @Override
    public double evaluate(V chromosome) {
        if (!cache.contains(chromosome)) {
            cache.put(chromosome, evaulator.evaluate(chromosome));
        }

        return cache.get(chromosome);
    }
}
