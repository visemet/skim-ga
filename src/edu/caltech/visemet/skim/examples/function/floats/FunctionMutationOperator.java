package edu.caltech.visemet.skim.examples.function.floats;

import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import edu.caltech.visemet.skim.NumberBase;
import edu.caltech.visemet.skim.genes.number.ByteBase;
import edu.caltech.visemet.skim.genes.number.ByteGene;

/**
 *
 * @param <T> the type of value for bases of genes of this mutation operator
 * @param <S> the type of base for genes of this mutation operator
 * @param <U> the type of gene for this mutation operator
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionMutationOperator<
        T extends Float,
        S extends NumberBase<T>,
        U extends Gene<T, S>
> implements MutationOperator<T, S, U> {

    /**
     * Holds the mutation operator used by this mutation operator.
     */
    private final MutationOperator<
            Byte,
            NumberBase<Byte>,
            Gene<Byte, NumberBase<Byte>>
    > mutator;

    /**
     * Class constructor specifying the mutation operator.
     *
     * @param mutator the mutation operator used by this mutation operator
     */
    public FunctionMutationOperator(
            MutationOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > mutator) {

        validate(mutator);
        this.mutator = mutator;
    }

    /**
     * Checks whether the specified mutation operator is {@code null}.
     *
     * @param mutator the mutation operator to be checked
     *
     * @throws IllegalArgumentException if the specified mutation operator is
     * {@code null}
     */
    private void validate(
            MutationOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > mutator) {

        if (mutator == null) {
            throw new IllegalArgumentException(
                    "the mutation operator cannot be null");
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
    private byte[] convert(float value) {
        int length = Integer.SIZE;
        byte[] bytes = new byte[length];

        int bits = Float.floatToRawIntBits(value);

        for (int bitIndex = 0; bitIndex < length; bitIndex++) {
            bytes[bitIndex] = (byte) ((bits >> (length - bitIndex - 1)) & 0x1);
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
    private float convert(byte[] bytes) {
        int bits = 0;

        int length = bytes.length;
        for (int bitIndex = 0; bitIndex < length; bitIndex++) {
            bits += (bytes[bitIndex] & 0x1) << (length - bitIndex - 1);
        }

        return Float.intBitsToFloat(bits);
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
        float value = base.getValue();
        byte[] bytes = convert(value);

        int byteGenesLength = Integer.SIZE;

        Gene<Byte, NumberBase<Byte>> byteGene = new ByteGene<>(byteGenesLength);

        for (int byteBaseIndex = 0; byteBaseIndex < byteGenesLength;
                byteBaseIndex++) {

            ByteBase byteBase =
                    new ByteBase((byte) 0, (byte) 1, bytes[byteBaseIndex]);

            byteGene.setBaseAt(byteBaseIndex, byteBase);
        }

        return byteGene;
    }

    @Override
    public void mutate(double probability, U gene) {
        for (S base : gene.getSequence()) {
            Gene<Byte, NumberBase<Byte>> byteGene = convert(base);

            mutator.mutate(probability, byteGene);

            int byteGeneLength = byteGene.length();
            byte[] bytes = new byte[byteGeneLength];

            for (int byteBaseIndex = 0; byteBaseIndex < byteGeneLength;
                    byteBaseIndex++) {

                NumberBase<Byte> byteBase = byteGene.getBaseAt(byteBaseIndex);

                byte byteValue = byteBase.getValue();
                bytes[byteBaseIndex] = byteValue;
            }

            float value = convert(bytes);

            base.setValue((T) (Float) (Float.isNaN(value) ? 0.0f : value));
        }
    }
}
