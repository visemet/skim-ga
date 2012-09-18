package edu.caltech.visemet.skim.cache;

import edu.caltech.visemet.skim.AbstractCache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class RandomReplacementCache<K, V> extends AbstractCache<K, V> {

    public RandomReplacementCache(int maxSize) {
        super(maxSize);
    }

    private List<K> randomKeys(int num, Map<K, V> map) {
        List<K> keys = new ArrayList<>(map.keySet());

        Collections.shuffle(keys);

        return keys.subList(0, num);
    }

    @Override
    public void setMaxSize(int maxSize) {
        int num = Math.max(0, getMaxSize() - maxSize);
        for (K key : randomKeys(num, asMap())) {
            remove(key);
        }

        super.setMaxSize(maxSize);
    }

    @Override
    public void put(K key, V value) {
        if (isFull()) {
            for (K iterKey : randomKeys(1, asMap())) {
                remove(iterKey);
            }
        }

        super.put(key, value);
    }
}
