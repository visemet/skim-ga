package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator extends SelectionOperator {

    /**
     * Returns individuals from the results of mutation operations on the
     * specified list of individuals.
     *
     * @param individuals the list of individuals to modify
     *
     * @return a list of individuals representing mutated individuals from the
     * specified list of individuals
     */
    List<Individual> mutate(List<Individual> individuals);
}
