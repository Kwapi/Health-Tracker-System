/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.PhysicalHealth;

import Goals.Goal;
import Model.HKFDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author xfu13dcu
 */
public class WeightProgressTest {
    
    WeightProgress instance = new WeightProgress(1,new Weight(-1), new HKFDate(18,04,2015));

    public WeightProgressTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class WeightProgress.
     */
    //@Test
    public void testFind() throws Exception {
        System.out.println("find");
        int wpID = 1;
        int expResult = -1;
        int result = WeightProgress.find(wpID).getWeight().getGrams();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class WeightProgress.
     */
    //@Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int physHealthID = 1;
        int expResult = 2;
        //Know that there is only 2 elements in table with ID of 1
        int result = WeightProgress.findAll(physHealthID).size();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class WeightProgress.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        int physHealthId = 1;
        instance.persist(physHealthId);
        testFindAll(); //If this true persist worked
        testFind(); //If this is true persist worked too
    }

    /**
     * Test of calulateBMI method, of class WeightProgress.
     */
    @Test
    public void testCalulateBMI() throws Exception {
        System.out.println("calulateBMI");
        int expResult = -1;
        int result = instance.calulateBMI();
        assertEquals(expResult, result);
        testDelete();
    }

    /**
     * Test of delete method, of class WeightProgress.
     */
    
    public void testDelete() throws Exception {
        System.out.println("delete");
        instance.delete();
        assertEquals(-1,WeightProgress.find(1).getWeight().getGrams());
        System.out.println("END");
    }
    
    /**
     * Test of updateValue method, of class WeightProgress.
     */
    @Test
    public void testUpdateValue() throws Exception {
        System.out.println("updateValue");
        String valueName = "target";
        String newValue = "6";
        instance.updateValue(valueName, newValue);
        int result = Goal.find(instance.getID()).getTarget();
        assertEquals(6, result);
    }

    /**
     * Test of getID method, of class WeightProgress.
     */
    @Test
    public void testGetID() {
        System.out.println("getID - not testing, simple class");
    }

    /**
     * Test of getPhysicalHealthID method, of class WeightProgress.
     */
    @Test
    public void testGetPhysicalHealthID() {
        System.out.println("getPhysicalHealthID - not testing, simple class");
    }

    /**
     * Test of getWeight method, of class WeightProgress.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight - not testing, simple class");
    }

    /**
     * Test of getDate method, of class WeightProgress.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate - not testing, simple class");
    }

    /**
     * Test of setID method, of class WeightProgress.
     */
    @Test
    public void testSetID() {
        System.out.println("setID - not testing, simple class");
    }

    /**
     * Test of setPhysicalHealthID method, of class WeightProgress.
     */
    @Test
    public void testSetPhysicalHealthID() {
        System.out.println("setPhysicalHealthID - not testing, simple class");
    }
    /**
     * Test of setWeight method, of class WeightProgress.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight - not testing, simple class");
    }

    /**
     * Test of setDate method, of class WeightProgress.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate - not testing, simple class");
    }

    /**
     * Test of compareTo method, of class WeightProgress.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo - not testing, just uses HKFDate compareTo which is already tested");
    }
    
}
