package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Population {

    /**
     * Initializes this population.
     */
    void initialize();

    /**
     * Adds the specified individual to this population.
     *
     * @param individual the individual to include
     */
    void expand(Individual individual);

    /**
     * Adds the specified list of individuals to this population.
     *
     * @param individuals the list of individuals to include
     */
    void expand(List<Individual> individuals);

    /**
     * Returns the size of this population.
     *
     * @return the number of members of this population
     */
    int size();

    /**
     * Returns this population as a list.
     *
     * @return a list representation of this population
     */
    List<Individual> asList();
}
