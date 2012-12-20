package edu.caltech.visemet.skim.algorithms;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import edu.caltech.visemet.skim.AbstractGeneticAlgorithm;
import edu.caltech.visemet.skim.FitnessFunction;
import edu.caltech.visemet.skim.Individual;
import edu.caltech.visemet.skim.Population;

/**
 *
 * @author Max Hirschhorn #visemet
 */
@XStreamAlias("fixed-termination")
public class FixedTerminationGeneticAlgorithm<I extends Individual<I>>
extends AbstractGeneticAlgorithm<I> {

    @XStreamAlias("max-generation")
    private int maxGeneration = 100;

    private transient int currGeneration = 0;

    /**
     * Class constructor.
     */
    public FixedTerminationGeneticAlgorithm() { }

    @Override
    public void evolve(
            Population<I> population, FitnessFunction<I> function,
            Population<I> nextPopulation) {

        super.evolve(population, function, nextPopulation);
        currGeneration++;
    }

    @Override
    public boolean shouldTerminate() {
        return (currGeneration >= maxGeneration);
    }
}
