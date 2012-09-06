package edu.caltech.visemet.skim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class MappedGene<K, T, S extends Base<T>> implements Gene<T, S> {

    private List<K> keys;
    private Gene<T, S> gene;

    private Map<K, S> map = new HashMap<>();

    public MappedGene(List<K> keys, Gene<T, S> gene) {
        this.keys = keys;
        this.gene = gene;

        updateMap();
    }

    private void updateMap() {
        int length = Math.min(keys.size(), gene.length());
        for (int index = 0; index < length; index++) {
            map.put(keys.get(index), gene.getBaseAt(index));
        }
    }

    @Override
    public void initialize() {
        gene.initialize();
        updateMap();
    }

    @Override
    public void randomize(Random random) {
        gene.randomize(random);
        updateMap();
    }

    @Override
    public List<S> getSequence() {
        return gene.getSequence();
    }

    @Override
    public void setSequence(List<S> sequence) {
        gene.setSequence(sequence);
        updateMap();
    }

    @Override
    public S getBaseAt(int index) {
        return gene.getBaseAt(index);
    }

    public S getBaseWith(K key) {
        return map.get(key);
    }

    @Override
    public void setBaseAt(int index, S base) {
        gene.setBaseAt(index, base);
        updateMap();
    }

    public void setBaseWith(K key, S base) {
        map.put(key, base);
        updateMap();
    }

    @Override
    public int length() {
        return gene.length();
    }

    @Override
    public MappedGene<K, T, S> copy() {
        return new MappedGene<>(keys, gene.copy());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        K key = keys.get(0);
        sb.append(key).append(": ").append(getBaseWith(key));

        int length = length();
        for (int index = 1; index < length; index++) {
            key = keys.get(index);
            sb.append(", ").append(key).append(": ").append(getBaseWith(key));
        }

        sb.append("}");

        return sb.toString();
    }
}