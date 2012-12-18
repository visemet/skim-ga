package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @param <I> the type of individuals in this crossover operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator<I extends Individual<I>>
extends SelectionOperator<I> {

    /**
     * Returns individuals from the results of crossover operations on the
     * specified list of individuals.
     *
     * @param individuals the list of individuals to recombine
     *
     * @return a list of individuals representing crossed-over pairs of
     * individuals from the specified list of individuals
     */
    List<I> crossover(List<I> individuals);
}
