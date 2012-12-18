package edu.caltech.visemet.skim;

/**
 *
 * @param <I> the type of individuals in this population factory
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractPopulationFactory<I extends Individual<I>>
implements PopulationFactory<I> {

    /**
     * Class constructor.
     */
    public AbstractPopulationFactory() { }

    @Override
    public Population<I> create() {
        return new DefaultPopulation<>();
    }
}
