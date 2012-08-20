package edu.caltech.visemet.skim;

import java.util.Iterator;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Population {

    void initialize();

    boolean add(Chromosome chromosome);

    int size();

    Iterator<Chromosome> iterator();

    Chromosome[] toArray();
}
