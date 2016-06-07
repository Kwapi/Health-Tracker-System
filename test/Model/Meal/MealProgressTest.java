/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Meal;

import Model.HKFDate;
import java.util.ArrayList;
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
public class MealProgressTest {
    
    MealProgress instance = new MealProgress(new Meal("t1_name", 1), new HKFDate(), 10, 1);
    
    public MealProgressTest() {
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
     * Test of calcCalories method, of class MealProgress.
     */
    @Test
    public void testCalcCalories() {
        System.out.println("calcCalories");
                
        int expResult = 10;
        int result = instance.calcCalories();
        assertEquals(expResult, result);
    }

    /**
     * Test of find method, of class MealProgress.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int mpID = 1;
        int expResult = -1;
        int result = MealProgress.find(mpID).getAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class MealProgress.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int memberID = 1;
        int expResult = -1;
        int result = MealProgress.findAll(memberID).get(0).getAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class MealProgress.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        int memberID = -1;
        instance.persist(memberID);
    }

    /**
     * Test of compareTo method, of class MealProgress.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        MealProgress t0 = new MealProgress(new Meal("t0_name", 1), new HKFDate(), 10, 0);
        MealProgress t1 = new MealProgress(new Meal("t1_name", 1), new HKFDate(), 10, 1);
        MealProgress t2 = new MealProgress(new Meal("t2_name", 1), new HKFDate(), 10, 2);
        
        int lessThan = instance.compareTo(t0);
        int equal = instance.compareTo(t1);
        int greaterThan = instance.compareTo(t2);
        
        assertEquals(-1, lessThan);
        assertEquals(0, equal);
        assertEquals(1, greaterThan);        
    }

    /**
     * Test of delete method, of class MealProgress.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        instance.delete();
    }
    
}
