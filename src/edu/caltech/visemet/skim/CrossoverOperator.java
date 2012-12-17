package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator extends SelectionOperator {

    /**
     * Returns individuals from the results of crossover operations on the
     * specified list of individuals.
     *
     * @param individuals the list of individuals to recombine
     *
     * @return a list of individuals representing crossed-over pairs of
     * individuals from the specified list of individuals
     */
    List<Individual> crossover(List<Individual> individuals);
}
