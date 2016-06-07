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
public class HeightTest {
    
    Height instance;
    
    public HeightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Height();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toMetres method, of class Height.
     */
    @Test
    public void testToMetres() {
        System.out.println("toMetres");
        int feet = 1;
        int expResult = (int) 0.3048;
        
        int result = instance.toMetres(feet);
        assertEquals(expResult, result);
    }

    /**
     * Test of toFeet method, of class Height.
     */
    @Test
    public void testToFeet() {
        System.out.println("toFeet");
        int metres = 1;        
        int expResult = (int) 3.2808399;
        
        int result = instance.toFeet(metres);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Height.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        instance = new Height(9);
        String expResult = "9";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
