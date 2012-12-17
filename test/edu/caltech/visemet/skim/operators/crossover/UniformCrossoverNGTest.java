/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.caltech.visemet.skim.operators.crossover;

import edu.caltech.visemet.skim.genes.BooleanBase;
import edu.caltech.visemet.skim.genes.BooleanGene;
import java.util.ArrayList;
import java.util.List;
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
public class UniformCrossoverNGTest {

    public UniformCrossoverNGTest() {
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
     * Test of crossover method, of class UniformCrossover.
     */
    @Test
    public void testCrossover() {
        System.out.println("crossover");
        double probability = 1.0;
        List<BooleanGene<Boolean, BooleanBase>> parents = new ArrayList<>();
        int numParents = 3;
        for (int count = 0; count < numParents; count++) {
            parents.add(new BooleanGene<Boolean, BooleanBase>(10));
        }

        Random random = new Random();

        for (BooleanGene<Boolean, BooleanBase> parent : parents) {
            parent.initialize();
            parent.randomize(random);
        }

        UniformCrossover instance = new UniformCrossover();
        List<BooleanGene<Boolean, BooleanBase>> expResult = null;
        List<BooleanGene<Boolean, BooleanBase>> result = instance.crossover(probability, parents);
        // assertEquals(result, expResult);
        System.out.println(parents);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
