package edu.caltech.visemet.skim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @param <K> the type of key for this mapped gene
 * @param <T> the type of value for bases of this mapped gene
 * @param <S> the type of base for this mapped gene
 *
 * @author Max Hirschhorn #visemet
 */
public class MappedGene<K, T, S extends Base<T>> implements Gene<T, S> {

    /**
     * Holds the list of keys of this mapped gene.
     */
    private List<K> keys;

    /**
     * Holds the gene wrapped by this gene.
     */
    private Gene<T, S> gene;

    /**
     * Holds the map of keys to bases of the gene of this mapped gene.
     */
    private Map<K, S> map = new HashMap<>();

    /**
     * Class constructor specifying the list of keys and the gene.
     *
     * @param keys the list of keys of this mapped gene
     * @param gene the gene wrapped by this mapped gene
     */
    public MappedGene(List<K> keys, Gene<T, S> gene) {
        int numKeys = keys.size();
        int geneLength = gene.length();

        if (numKeys < geneLength) {
            throw new IllegalArgumentException("missing key for base");
        } else if (numKeys > geneLength) {
            throw new IllegalArgumentException("missing base for key");
        }

        this.keys = keys;
        this.gene = gene;

        updateMap();
    }

    private void updateMap() {
        int length = length();
        for (int index = 0; index < length; index++) {
            updateMap(keys.get(index), gene.getBaseAt(index));
        }
    }

    private void updateMap(K key, S base) {
        map.put(key, base);
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
        return getBaseWith(keys.get(index));
    }

    /**
     * Returns the base with the specified identifier in the sequence of this
     * mapped gene.
     *
     * @param key the key of the base to return
     *
     * @return the base with the specified identifier in the sequence of this
     * gene
     */
    public S getBaseWith(K key) {
        return map.get(key);
    }

    @Override
    public void setBaseAt(int index, S base) {
        setBaseWith(keys.get(index), base);
    }

    /**
     * Replaces the base with the specified identifier in the sequence of this
     * mapped gene with the specified base.
     *
     * @param key the identifier of the base to replace
     * @param base the base to be stored with the specified identifier
     */
    public void setBaseWith(K key, S base) {
        map.put(key, base);
        updateMap(key, base);
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
    public int hashCode() {
        return Objects.hash(this.keys, this.gene);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final MappedGene<K, T, S> other = (MappedGene<K, T, S>) obj;

        return (Objects.equals(this.keys, other.keys)
                && Objects.equals(this.gene, other.gene));
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
