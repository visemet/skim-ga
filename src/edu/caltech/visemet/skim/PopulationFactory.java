package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface PopulationFactory<I extends Individual<I>> {

    /**
     * Creates an empty, uninitialized population.
     *
     * @return an empty, uninitialized population
     */
    Population<I> create();

    /**
     * Adds individuals to the specified population.
     *
     * @param population the population to expand
     */
    void expand(Population<I> population);
}
