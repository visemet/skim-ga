package edu.caltech.visemet.skim;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Gene<T, S extends Base<T>> {

    void initialize();

    void randomize(Random random);

    List<S> getSequence();

    void setSequence(List<S> sequence);

    S getBaseAt(int index);

    void setBaseAt(int index, S base);

    int length();

    Gene<T, S> copy();
}
