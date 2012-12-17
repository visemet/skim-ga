package edu.caltech.visemet.skim.examples.function.doubles;

import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.Example;
import edu.caltech.visemet.skim.ExampleConfiguration;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.NumberBase;
import edu.caltech.visemet.skim.genes.number.DoubleBase;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class IdentityFunctionExample<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> extends FunctionExample<T, S, U, V> {

    public IdentityFunctionExample(ExampleConfiguration config) {
        super(new Function() {

            @Override
            public double evaluate(double... vars) {
                if (vars.length != 1) {
                    throw new IllegalArgumentException("invalid number of variables");
                }

                double var = vars[0];

                // return Short.MAX_VALUE - Math.pow(var - 5, 2);
                // return -Math.pow(var - 5, 2);
                return 1.0 / (1 + Math.pow(var - 5, 2));
            }
        }, config);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Example<
                Double,
                DoubleBase,
                Gene<Double, DoubleBase>,
                Chromosome<
                        Double,
                        DoubleBase,
                        Gene<Double, DoubleBase>
                >
        > example = new IdentityFunctionExample<>(null);

        example.configure();
        example.execute();
    }
}
