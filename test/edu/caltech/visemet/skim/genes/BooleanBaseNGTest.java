package edu.caltech.visemet.skim.genes;

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
public class BooleanBaseNGTest {

    private BooleanBase trueBase;

    private BooleanBase falseBase;

    public BooleanBaseNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        trueBase = new BooleanBase(true);
        falseBase = new BooleanBase(false);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        trueBase = null;
        falseBase = null;
    }

    /**
     * Test of randomize method, of class BooleanBase.
     */
    @Test
    public void testRandomize() {
        System.out.println("randomize");
        Random random = new Random();
        BooleanBase instance;

        // trueBase.randomize()
        //      ==> trueBase.getValue() -> true  [0.5]
        //                              -> false [0.5]

        int numTrials = 100000;
        int numTrue = 0;
        int numFalse = 0;

        instance = trueBase;
        for (int count = 0; count < numTrials; count++) {
            instance.randomize(random);

            if (instance.getValue()) {
                numTrue++;
            } else {
                numFalse++;
            }
        }

        assertEquals(numTrue, numFalse, numTrials * 0.01);
    }

    /**
     * Test of getValue method, of class BooleanBase.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        BooleanBase instance;
        Boolean expResult;
        Boolean result;

        // trueBase.getValue() -> true
        instance = trueBase;
        expResult = true;
        result = instance.getValue();
        assertEquals(result, expResult);

        // falseBase.getValue() -> false
        instance = falseBase;
        expResult = false;
        result = instance.getValue();
        assertEquals(result, expResult);
    }

    /**
     * Test of setValue method, of class BooleanBase.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        Boolean value;
        BooleanBase instance;
        Boolean expResult;
        Boolean result;

        // trueBase.setValue(false)
        //      ==> trueBase.getValue() -> false
        value = false;
        instance = trueBase;
        instance.setValue(value);

        expResult = value;
        result = instance.getValue();
        assertEquals(result, expResult);

        // falseBase.setValue(true)
        //      ==> falseBase.getValue() -> true
        value = true;
        instance = falseBase;
        instance.setValue(value);

        expResult = value;
        result = instance.getValue();
        assertEquals(result, expResult);
    }

    /**
     * Test of copy method, of class BooleanBase.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        BooleanBase instance;
        BooleanBase expResult;
        BooleanBase result;

        // trueBase.copy() -> new BooleanBase(true)
        //      ==> trueBase.equals(new BooleanBase(true)) -> true
        //      ==> trueBase == new BooleanBase(true) -> false
        instance = trueBase;
        expResult = instance;
        result = instance.copy();

        assertEquals(result, expResult);
        assertNotSame(result, expResult);

        // falseBase.copy() -> new BooleanBase(false)
        //      ==> falseBase.equals(new BooleanBase(false)) -> true
        //      ==> falseBase == new BooleanBase(false) -> false
        instance = trueBase;
        expResult = instance;
        result = instance.copy();

        assertEquals(result, expResult);
        assertNotSame(result, expResult);
    }

    /**
     * Test of hashCode method, of class BooleanBase.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        BooleanBase instance;
        int expResult;
        int result;

        instance = trueBase;
        expResult = instance.copy().hashCode();
        result = instance.hashCode();
        assertEquals(result, expResult);

        int numTrials = 100000;

        instance = trueBase;
        for (int count = 0; count < numTrials; count++) {
            expResult = instance.hashCode();
            result = instance.hashCode();
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of equals method, of class BooleanBase.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        BooleanBase other;
        BooleanBase instance;
        boolean expResult;
        boolean result;

        // trueBase.equals(trueBase) -> true
        other = trueBase;
        instance = trueBase;
        expResult = true;
        result = instance.equals(other);
        assertEquals(result, expResult);

        // trueBase.equals(trueBase.copy()) -> true
        other = trueBase.copy();
        instance = trueBase;
        expResult = true;
        result = instance.equals(other);
        assertEquals(result, expResult);

        // trueBase.equals(trueBase.copy().copy()) -> true
        other = trueBase.copy().copy();
        instance = trueBase;
        expResult = true;
        result = instance.equals(other);
        assertEquals(result, expResult);

        // trueBase.equals(falseBase) -> false
        other = falseBase;
        instance = trueBase;
        expResult = false;
        result = instance.equals(other);
        assertEquals(result, expResult);

        // falseBase.equals(trueBase) -> false
        other = trueBase;
        instance = falseBase;
        expResult = false;
        result = instance.equals(other);
        assertEquals(result, expResult);
    }

    /**
     * Test of toString method, of class BooleanBase.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BooleanBase instance;
        String expResult;
        String result;

        // trueBase.toString() -> "1"
        instance = trueBase;
        expResult = "1";
        result = instance.toString();
        assertEquals(result, expResult);

        // falseBase.toString() -> "0"
        instance = falseBase;
        expResult = "0";
        result = instance.toString();
        assertEquals(result, expResult);
    }
}
