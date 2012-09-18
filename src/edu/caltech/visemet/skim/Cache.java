package edu.caltech.visemet.skim;

import java.util.Map;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public interface Cache<K, V> {

    int getMaxSize();

    void setMaxSize(int maxSize);

    int size();

    boolean isFull();

    boolean contains(K key);

    V get(K key);

    void put(K key, V value);

    V remove(K key);

    void clear();

    Map<K, V> asMap();
}
