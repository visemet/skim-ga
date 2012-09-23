package edu.caltech.visemet.skim.examples.function.doubles;

import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.MutationOperator;
import edu.caltech.visemet.skim.NumberBase;
import edu.caltech.visemet.skim.genes.number.ByteBase;
import edu.caltech.visemet.skim.genes.number.ByteGene;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionMutationOperator<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>
> implements MutationOperator<T, S, U> {

    private MutationOperator<
            Byte,
            NumberBase<Byte>,
            Gene<Byte, NumberBase<Byte>>
    > mutator;

    public FunctionMutationOperator(
            MutationOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > mutator) {

        this.mutator = mutator;
    }

    @Override
    public void mutate(double probability, U gene) {
        S base = gene.getBaseAt(0);

        double value = base.getValue();
        long bits = Double.doubleToRawLongBits(value);

        int newGeneLength = Long.SIZE;

        byte[] bytes = new byte[newGeneLength];
        for (int bitIndex = 0; bitIndex < newGeneLength; bitIndex++) {
            bytes[bitIndex] =
                    (byte) ((bits >> (bytes.length - bitIndex - 1)) & 0x1L);
        }

        Gene<Byte, NumberBase<Byte>> newGene = new ByteGene<>(newGeneLength);
        for (int newBaseIndex = 0; newBaseIndex < newGeneLength;
                newBaseIndex++) {

            ByteBase newBase =
                    new ByteBase((byte) 0, (byte) 1, bytes[newBaseIndex]);

            newGene.setBaseAt(newBaseIndex, newBase);
        }

        mutator.mutate(probability, newGene);

        for (int newBaseIndex = 0; newBaseIndex < newGeneLength;
                newBaseIndex++) {

            NumberBase<Byte> newBase = newGene.getBaseAt(newBaseIndex);

            byte newValue = newBase.getValue();
            bytes[newBaseIndex] = newValue;
        }

        bits = 0;
        for (int byteIndex = 0; byteIndex < newGeneLength; byteIndex++) {
            bits += ((long) bytes[byteIndex] & 0x1L)
                    << (newGeneLength - byteIndex - 1);
        }

        value = Double.longBitsToDouble(bits);

        base.setValue((T) (Double) (Double.isNaN(value) ? 0.0 : value));
    }
}
