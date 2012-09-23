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
 * @author Max Hirschhorn #visemet
 */
public class FunctionCrossoverOperator<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>
> implements CrossoverOperator<T, S, U> {

    private CrossoverOperator<
            Byte,
            NumberBase<Byte>,
            Gene<Byte, NumberBase<Byte>>
    > crossover;

    public FunctionCrossoverOperator(
            CrossoverOperator<
                    Byte,
                    NumberBase<Byte>,
                    Gene<Byte, NumberBase<Byte>>
            > crossover) {

        this.crossover = crossover;
    }

    @Override
    public List<U> crossover(double probability, List<U> parents) {
        int numParents = parents.size();
        List<U> children = new ArrayList<>(numParents);

        List<Gene<Byte, NumberBase<Byte>>> newParents =
                new ArrayList<>(numParents);

        for (U parent : parents) {
            S base = parent.getBaseAt(0);

            double value = base.getValue();
            long bits = Double.doubleToRawLongBits(value);

            int newGeneLength = Long.SIZE;

            byte[] bytes = new byte[newGeneLength];
            for (int bitIndex = 0; bitIndex < newGeneLength; bitIndex++) {
                bytes[bitIndex] =
                        (byte) ((bits >> (bytes.length - bitIndex - 1)) & 0x1L);
            }

            Gene<Byte, NumberBase<Byte>> newGene =
                    new ByteGene<>(newGeneLength);

            for (int newBaseIndex = 0; newBaseIndex < newGeneLength;
                    newBaseIndex++) {

                ByteBase newBase =
                        new ByteBase((byte) 0, (byte) 1, bytes[newBaseIndex]);

                newGene.setBaseAt(newBaseIndex, newBase);
            }

            newParents.add(newGene);
        }

        List<Gene<Byte, NumberBase<Byte>>> newChildren =
                crossover.crossover(probability, newParents);

        for (int parentIndex = 0; parentIndex < numParents; parentIndex++) {
            Gene<Byte, NumberBase<Byte>> newChild =
                    newChildren.get(parentIndex);

            int newGeneLength = newChild.length();

            byte[] bytes = new byte[newGeneLength];

            for (int newBaseIndex = 0; newBaseIndex < newGeneLength;
                    newBaseIndex++) {

                NumberBase<Byte> base = newChild.getBaseAt(newBaseIndex);

                byte value = base.getValue();
                bytes[newBaseIndex] = value;
            }

            long bits = 0;
            for (int bitIndex = 0; bitIndex < newGeneLength; bitIndex++) {
                bits += ((long) bytes[bitIndex] & 0x1L)
                        << (newGeneLength - bitIndex - 1);
            }

            double value = Double.longBitsToDouble(bits);

            U parent = parents.get(parentIndex);
            U child = (U) parent.copy();

            S childBase = child.getBaseAt(0);
            childBase.setValue(
                    (T) (Double) (Double.isNaN(value) ? 0.0 : value));

            children.add(child);
        }

        return children;
    }
}
