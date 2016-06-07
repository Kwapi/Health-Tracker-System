/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Model.Meal.Meal;
import Model.Meal.MealProgress;
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
public class DietLoggerTest {
    
    DietLogger instance = new DietLogger();
    
    public DietLoggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                        
        instance.addMealProgress(new MealProgress(new Meal(2,"t1_mealName1", 10), new HKFDate("2015-04-16"), 2, 2));
        instance.addMealProgress(new MealProgress(new Meal(1,"t1_mealName2", 9), new HKFDate("2014-05-25"), 4, 1));
        instance.addMealProgress(new MealProgress(new Meal(1,"t1_mealName3", 11), new HKFDate("2015-07-12"), 1, 0));
        instance.addMealProgress(new MealProgress(new Meal(3,"t1_mealName4", 15), new HKFDate("2015-09-11"), 2, 3));
        instance.addMealProgress(new MealProgress(new Meal(4,"t1_mealName5", 5), new HKFDate("2012-012-06"), 32, 3));
        
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of addMealProgress method, of class DietLogger.
     */
    @Test
    public void testAddMealProgress() {
        System.out.println("addMealProgress");
        MealProgress ep = new MealProgress(new Meal("t1_mealName", 10), new HKFDate("2015-04-16"), 2, 2);
        int instanceSize = instance.getMealLog().size()+1; //Means that there has been something added to the instance
        instance.addMealProgress(ep);
        assertEquals(instance.getMealLog().size(), instanceSize);                
    }

    /**
     * Test of findTotalCalsConsumed method, of class DietLogger.
     */
    @Test
    public void testFindTotalCalsConsumed() {
        System.out.println("findTotalCalsConsumed - Not Done Yet");        
        instance.findTotalCalsConsumed();
    }

    /**
     * Test of findAverageWeeklyCalsConsumed method, of class DietLogger.
     */
    @Test
    public void testFindAverageWeeklyCalsConsumed() {
        System.out.println("findAverageWeeklyCalsConsumed - Not Done Yet");       
        instance.findAverageWeeklyCalsConsumed();
    }

    /**
     * Test of findTotalCalsConsumedThisWeek method, of class DietLogger.
     */
    @Test
    public void testFindTotalCalsConsumedThisWeek() {
        System.out.println("findTotalCalsConsumedThisWeek - Not Done Yet");        
        instance.findTotalCalsConsumedThisWeek();
    }

    /**
     * Test of findAverageDailyCalsConsumed method, of class DietLogger.
     */
    @Test
    public void testFindAverageDailyCalsConsumed() {
        System.out.println("findAverageDailyCalsConsumed - Not Done Yet");        
        instance.findAverageDailyCalsConsumed();
    }

    /**
     * Test of findTotalCalsConsumedToday method, of class DietLogger.
     */
    @Test
    public void testFindTotalCalsConsumedToday() {
        System.out.println("findTotalCalsConsumedToday - Not Done Yet");        
        instance.findTotalCalsConsumedToday();
    }

    /**
     * Test of sortDate method, of class DietLogger.
     */
    @Test
    public void testSortDate() {
        System.out.println("sortDate Test");
        for(int i = 0; i < instance.getMealLog().size(); i++) {
            System.out.println(instance.getMealLog().get(i));
        }
        instance.sortDate();
        for(int i = 0; i < instance.getMealLog().size(); i++) {
            System.out.println(instance.getMealLog().get(i));
        }
    }

    /**
     * Test of find method, of class DietLogger.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int memberID = 1;
        
        MealProgress mProgress = new MealProgress(Meal.find(1), new HKFDate("2015-04-14"), 1, 2);
                
        DietLogger result = DietLogger.find(memberID);
        
        assertEquals(mProgress.getID(),result.getMealLog().get(0).getID());
    }

    /**
     * Test of persist method, of class DietLogger.
     */
    @Test
    public void testPersist_0args() throws Exception {
        System.out.println("persist");
        int id = 9;
        DietLogger toAdd = new DietLogger();
        toAdd.persist();
        DietLogger result = DietLogger.find(id);
    }

    /**
     * Test of persist method, of class DietLogger.
     */
    @Test
    public void testPersist_MealProgress() throws Exception {
        System.out.println("persist");
        MealProgress mealProg = null;
        
        instance.persist(mealProg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
