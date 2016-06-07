/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Meal;

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
public class MealTest {
    
    public MealTest() {
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
     * Test of find method, of class Meal.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int mealID = 1;
        String expResult = "t_name";
        String result = Meal.find(mealID).getMealName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class Meal.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        String expResult = "t_name";
        String result = Meal.findAll().get(0).getMealName();
        
        assertEquals(expResult, result);
    }
    
}
