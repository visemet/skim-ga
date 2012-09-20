package edu.caltech.visemet.skim;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    private int maxSize;

    private Map<K, V> map = new HashMap<>();

    public AbstractCache(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maximum size must be positive");
        }

        this.maxSize = maxSize;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isFull() {
        return (size() == getMaxSize());
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        put(key, value, true);
    }

    private void put(K key, V value, boolean replaceKey) {
        if (replaceKey) {
            remove(key);
        }

        map.put(key, value);
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(map);
    }
}
