package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @param <I> the type of individuals in this mutation operator
 *
 * @author Max Hirschhorn #visemet
 */
public interface MutationOperator<I extends Individual<I>>
extends SelectionOperator<I> {

    /**
     * Returns individuals from the results of mutation operations on the
     * specified list of individuals.
     *
     * @param individuals the list of individuals to modify
     *
     * @return a list of individuals representing mutated individuals from the
     * specified list of individuals
     */
    List<I> mutate(List<I> individuals);
}
