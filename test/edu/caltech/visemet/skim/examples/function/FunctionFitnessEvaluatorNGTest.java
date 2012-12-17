/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.caltech.visemet.skim.examples.function;

import edu.caltech.visemet.skim.examples.function.doubles.Function;
import edu.caltech.visemet.skim.examples.function.doubles.FunctionFitnessEvaluator;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.DefaultChromosome;
import edu.caltech.visemet.skim.genes.number.ByteBase;
import edu.caltech.visemet.skim.genes.number.ByteGene;
import edu.caltech.visemet.skim.genes.number.DoubleBase;
import edu.caltech.visemet.skim.genes.number.DoubleGene;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionFitnessEvaluatorNGTest {

    public FunctionFitnessEvaluatorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of evaluate method, of class FunctionFitnessEvaluator.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");

        Random random = new Random();

        // int geneLength = 64;

        /* Sign */
        // If min value >= 0, then @index=0, value = 0.
        // Else if maxvalue <= 0, then @index=0, value = 1.

        /* Exponent */
        // Assume mantissa is 0xfffffffffffff
        // Use 0x7ff mask to get exponent part
        // Formula is 2 ^ (exponent - 1023)

        // 0x1fffffffffffff -> 9007199254740991

        // log(2 ^ (exponent - 1023)) / log(10) = power
        //      ==> exponent = log(exp(log(10) * power)) / log(2) + 1023
        //                   = log(10) * power / log(2) + 1023
        //                   = log(10) * (log(value) / log(10)) / log(2) + 1023
        //                   = log(value) / log(2) + 1023 ... derp

        // Apply the previous formula to the maximum and minimum values specified,
        // forcing any bases to have assigned value for all bits of the exponent
        // that are the same for both until they differ.

        /* Mantissa */
        // floor((value / 2^(mantissa - 1023) - 1) * 2^52) to base 16

        /* ByteGene<Byte, ByteBase> gene = new ByteGene<>(geneLength);
        for (int index = 0; index < geneLength; index++) {
            ByteBase base = new ByteBase((byte) 0, (byte) 1, (byte) 0);
            base.randomize(random);

            gene.setBaseAt(index, base);
        }

        Chromosome<
                Byte,
                ByteBase,
                ByteGene<Byte, ByteBase>
        > chromosome = new DefaultChromosome(
                new ArrayList(Collections.singleton(gene)));

        FunctionFitnessEvaluator<
                Byte,
                ByteBase,
                ByteGene<Byte, ByteBase>,
                Chromosome<Byte, ByteBase, ByteGene<Byte, ByteBase>>
        > instance = new FunctionFitnessEvaluator<>(new Function() {

            @Override
            public double evaluate(double... vars) {
                double var1 = vars[0];
                return (var1 % 200) -100;
            }
        }); */

        int geneLength = 1;

        DoubleGene<Double, DoubleBase> gene = new DoubleGene<>(geneLength);
        for (int index = 0; index < geneLength; index++) {
            DoubleBase base = new DoubleBase(-100.0, 100.0, 0.0);
            base.randomize(random);

            gene.setBaseAt(index, base);
        }

        Chromosome<
                Double,
                DoubleBase,
                DoubleGene<Double, DoubleBase>
        > chromosome = new DefaultChromosome(
                new ArrayList(Collections.singleton(gene)));

        FunctionFitnessEvaluator<
                Double,
                DoubleBase,
                DoubleGene<Double, DoubleBase>,
                Chromosome<Double, DoubleBase, DoubleGene<Double, DoubleBase>>
        > instance = new FunctionFitnessEvaluator<>(new Function() {

            @Override
            public double evaluate(double... vars) {
                double var1 = vars[0];
                return var1;
            }
        });

        double expResult = 0.0;
        double result = instance.evaluate(chromosome);
        System.out.println(result);
        // assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
}
