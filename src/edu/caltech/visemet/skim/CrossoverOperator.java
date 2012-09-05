package edu.caltech.visemet.skim;

import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface CrossoverOperator<T, S extends Base<T>, U extends Gene<T, S>> {

    U crossover(double probability, U parent, List<U> otherParents);
}
