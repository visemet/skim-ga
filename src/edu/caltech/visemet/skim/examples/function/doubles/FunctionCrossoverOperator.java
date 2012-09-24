package edu.caltech.visemet.skim.examples.function.doubles;

import edu.caltech.visemet.skim.CrossoverOperator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.NumberBase;
import edu.caltech.visemet.skim.genes.number.ByteBase;
import edu.caltech.visemet.skim.genes.number.ByteGene;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> the type of value for bases of genes of this crossover operator
 * @param <S> the type of base for genes of this crossover operator
 * @param <U> the type of gene for this crossover operator
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionCrossoverOperator<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    /**
     * Holds the crossover operator used by this crossover operator.
     */
    private final CrossoverOperator<
            Byte,
            NumberBase<Byte>,
            Gene<Byte, NumberBase<Byte>>
    > crossover;

    /**
     * Class constructor specifying the crossover operator.
     *
     * @param crossover the crossover operator used by this crossover operator
     */
    public FunctionCrossoverOperator(
            CrossoverOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > crossover) {

        validate(crossover);
        this.crossover = crossover;
    }

    /**
     * Checks whether the specified crossover operator is {@code null}.
     *
     * @param crossover the crossover operator to be checked
     *
     * @throws IllegalArgumentException if the specified crossover operator is
     * {@code null}
     */
    private void validate(
            CrossoverOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > crossover) {

        if (crossover == null) {
            throw new IllegalArgumentException(
                    "the crossover operator cannot be null");
        }
    }

    /**
     * Returns a {@code byte} array that represents the binary form of the
     * specified value.
     *
     * @param value the value to be converted
     *
     * @return a {@code byte} array that represents the binary form of the
     * specified value
     */
    private byte[] convert(double value) {
        int length = Long.SIZE;
        byte[] bytes = new byte[length];

        long bits = Double.doubleToRawLongBits(value);

        for (int bitIndex = 0; bitIndex < length; bitIndex++) {
            bytes[bitIndex] = (byte) ((bits >> (length - bitIndex - 1)) & 0x1L);
        }

        return bytes;
    }

    /**
     * Returns a value representing the value of the {@code byte} array, as
     * interpreted in binary form.
     *
     * @param bytes the array to be converted
     *
     * @return a value representing the value of the {@code byte} array, as
     * interpreted in binary form
     */
    private double convert(byte[] bytes) {
        long bits = 0;

        int length = bytes.length;
        for (int bitIndex = 0; bitIndex < length; bitIndex++) {
            bits += ((long) bytes[bitIndex] & 0x1L) << (length - bitIndex - 1);
        }

        return Double.longBitsToDouble(bits);
    }

    /**
     * Returns a {@code ByteGene} instance with bases representing the binary
     * form of the specified base.
     *
     * @param base the base to be converted
     *
     * @return a {@code ByteGene} instance with bases representing the binary
     * form of the specified base
     */
    private Gene<Byte, NumberBase<Byte>> convert(S base) {
        double value = base.getValue();
        byte[] bytes = convert(value);

        int byteGenesLength = Long.SIZE;

        Gene<Byte, NumberBase<Byte>> byteGene = new ByteGene<>(byteGenesLength);

        for (int byteBaseIndex = 0; byteBaseIndex < byteGenesLength;
                byteBaseIndex++) {

            ByteBase byteBase =
                    new ByteBase((byte) 0, (byte) 1, bytes[byteBaseIndex]);

            byteGene.setBaseAt(byteBaseIndex, byteBase);
        }

        return byteGene;
    }

    /**
     * Returns a list of {@code ByteGene} instances with bases representing the
     * binary form of the specified genes at the specified position.
     *
     * @param baseIndex the index of the base to be converted
     * @param genes the genes to be converted
     *
     * @return a list of {@code ByteGene} instances with bases representing the
     * binary form of the specified genes at the specified position
     */
    private List<Gene<Byte, NumberBase<Byte>>> convert(
            int baseIndex, List<U> genes) {

        int numGenes = genes.size();

        List<Gene<Byte, NumberBase<Byte>>> byteGenes =
                new ArrayList<>(numGenes);

        for (U gene : genes) {
            S base = gene.getBaseAt(baseIndex);
            Gene<Byte, NumberBase<Byte>> byteGene = convert(base);

            byteGenes.add(byteGene);
        }

        return byteGenes;
    }

    @Override
    public List<U> crossover(double probability, List<U> parents) {
        int numParents = parents.size();
        int numChildren = getNumChildren(numParents);

        if (crossover.getNumChildren(numParents) != numChildren) {
            throw new IllegalStateException(
                    "crossover operator cannot return different number of "
                    + "children than parents");
        }

        List<U> children = new ArrayList<>(numChildren);

        int geneLength = -1;

        for (U parent : parents) {
            int parentGeneLength = parent.length();

            if (geneLength == -1) {
                geneLength = parentGeneLength;
            } else if (parentGeneLength != geneLength) {
                throw new IllegalArgumentException(
                        "parents must have same gene length");
            }

            U child = (U) parent.copy();
            children.add(child);
        }

        for (int baseIndex = 0; baseIndex < geneLength; baseIndex++) {
            List<Gene<Byte, NumberBase<Byte>>> byteParents =
                    convert(baseIndex, parents);

            List<Gene<Byte, NumberBase<Byte>>> byteChildren =
                    crossover.crossover(probability, byteParents);

            for (int childIndex = 0; childIndex < numChildren; childIndex++) {
                Gene<Byte, NumberBase<Byte>> byteChild =
                        byteChildren.get(childIndex);

                int byteGeneLength = byteChild.length();
                byte[] bytes = new byte[byteGeneLength];

                for (int byteBaseIndex = 0; byteBaseIndex < byteGeneLength;
                        byteBaseIndex++) {

                    NumberBase<Byte> base = byteChild.getBaseAt(byteBaseIndex);

                    byte value = base.getValue();
                    bytes[byteBaseIndex] = value;
                }

                double value = convert(bytes);

                U child = children.get(childIndex);
                S childBase = child.getBaseAt(baseIndex);

                childBase.setValue(
                        (T) (Double) (Double.isNaN(value) ? 0.0 : value));
            }
        }

        return children;
    }

    @Override
    public int getNumChildren(int numParents) {
        return numParents;
    }
}
