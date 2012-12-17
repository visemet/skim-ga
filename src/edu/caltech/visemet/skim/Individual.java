package edu.caltech.visemet.skim;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Individual {

    /**
     * Initializes this individual.
     */
    void initialize();

    /**
     * Modifies this individual.
     */
    void mutate();

    /**
     * Returns a copy of this individual.
     *
     * @return another individual, identical to this instance
     */
    Individual copy();
}
