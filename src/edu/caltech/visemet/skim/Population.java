package edu.caltech.visemet.skim;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Population<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> {

    void initialize();

    boolean add(V chromosome);

    int size();

    Iterator<V> iterator();

    List<V> toList();
}
