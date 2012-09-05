package edu.caltech.visemet.skim;

import java.util.Iterator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Population<T, S extends Base<T>, U extends Gene<T, S>, V extends Chromosome<T, S, U>> {

    void initialize();

    boolean add(V chromosome);

    int size();

    Iterator<V> iterator();

    V[] toArray();
}
