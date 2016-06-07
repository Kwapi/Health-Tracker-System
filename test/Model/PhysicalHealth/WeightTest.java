/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.PhysicalHealth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xfu13dcu
 */
public class WeightTest {
    
    Weight instance;
    
    public WeightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Weight();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of toPounds method, of class Weight.
     */
    @Test
    public void testToPounds() {
        System.out.println("toPounds");
        double kilos = 1.0;
        double expResult = 2.20462;
        double result = instance.toPounds(kilos);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toKilos method, of class Weight.
     */
    @Test
    public void testToKilos() {
        System.out.println("toKilos");
        double pounds = 1.0;
        double expResult = 0.453592;
        double result = instance.toKilos(pounds);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toString method, of class Weight.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Weight instance = new Weight(1000);
        String expResult = "1.0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of forGraph method, of class Weight.
     */
    @Test
    public void testForGraph() {
        System.out.println("forGraph");
        Weight instance = new Weight(10);
        String expResult = "10";
        String result = instance.forGraph();
        assertEquals(expResult, result);
    }

    /**
     * Test of toKg method, of class Weight.
     */
    @Test
    public void testToKg() {
        System.out.println("toKg");
        Weight instance = new Weight(1000);
        double expResult = 1.0;
        double result = instance.toKg();
        assertEquals(expResult, result, 0.0);
    }
    
}
