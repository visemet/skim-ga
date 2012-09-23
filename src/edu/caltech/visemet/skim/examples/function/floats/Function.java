package edu.caltech.visemet.skim.examples.function.floats;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Function {

    /**
     * Returns a value based on the specified variables of this function.
     *
     * @param vars the parameters used by this function
     *
     * @return a value based on the specified variables of this function
     */
    double evaluate(float... vars);
}
