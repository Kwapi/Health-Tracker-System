/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xmw13bzu
 */
public class ExerciseLoggerTest {
    
    ExerciseLogger exLog;
    
    public ExerciseLoggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Exercise exercise = new Exercise("Running", "time", 5);
        
        ExerciseProgress exProg1 = new ExerciseProgress(exercise, new HKFDate("2015-04-01"), 10, 10);
        ExerciseProgress exProg2 = new ExerciseProgress(exercise, new HKFDate("2015-04-04"), 10, 10);
        ExerciseProgress exProg3 = new ExerciseProgress(exercise, new HKFDate("2015-04-07"), 10, 10);
        ExerciseProgress exProg4 = new ExerciseProgress(exercise, new HKFDate("2015-04-10"), 10, 10);
        ExerciseProgress exProg5 = new ExerciseProgress(exercise, new HKFDate("2015-04-13"), 10, 10);
        ExerciseProgress exProg6 = new ExerciseProgress(exercise, new HKFDate("2015-04-16"), 10, 10);
        ExerciseProgress exProg7 = new ExerciseProgress(exercise, new HKFDate("2015-04-19"), 10, 10);
        
        exLog = new ExerciseLogger();
        
        exLog.addExerciseProgress(exProg1);
        exLog.addExerciseProgress(exProg2);
        exLog.addExerciseProgress(exProg3);
        exLog.addExerciseProgress(exProg4);
        exLog.addExerciseProgress(exProg5);
        exLog.addExerciseProgress(exProg6);
        exLog.addExerciseProgress(exProg7);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addExerciseProgress method, of class ExerciseLogger.
     */
    @Test
    public void testAddExerciseProgress() {
        System.out.println("addExerciseProgress");
        ExerciseProgress ep = null;
        ExerciseLogger instance = new ExerciseLogger();
        instance.addExerciseProgress(ep);
        
        assertTrue(instance.getExerciseLog().size() == 1);
        assertTrue(instance.getExerciseLog().get(0) == null);
    }

    /**
     * Test of findTotalCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurned() {
        System.out.println("findTotalCalsBurned");
        
        ExerciseLogger instance = new ExerciseLogger();
        assertTrue(instance.findTotalCalsBurned() == 0);
        assertTrue(exLog.findTotalCalsBurned() == (10*5*7));
    }

    /**
     * Test of findAverageWeeklyCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindAverageWeeklyCalsBurned() {
        System.out.println("findAverageWeeklyCalsBurned");
        
        ExerciseLogger instance = new ExerciseLogger();
        assertTrue(instance.findAverageWeeklyCalsBurned() == 0);
        
        assertTrue(exLog.findAverageWeeklyCalsBurned() == exLog.findTotalCalsBurned() / 3);
    }

    /**
     * Test of findTotalCalsBurnedThisWeek method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurnedThisWeek() {
        System.out.println("findTotalCalsBurnedThisWeek");
        
        ExerciseLogger instance = new ExerciseLogger();
        assertTrue(instance.findTotalCalsBurnedThisWeek() == 0);
        
        exLog.addExerciseProgress(new ExerciseProgress(new Exercise("Running", "time", 5), new HKFDate(), 10, 10));
        assertTrue(exLog.findTotalCalsBurnedThisWeek() == (10*5));
    }

    /**
     * Test of findAverageDailyCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindAverageDailyCalsBurned() {
        System.out.println("findAverageDailyCalsBurned");  
                
        ExerciseLogger instance = new ExerciseLogger();
        assertTrue(instance.findAverageDailyCalsBurned() == 0);
        
        assertTrue(exLog.findAverageDailyCalsBurned() == exLog.findTotalCalsBurned() / 19);
    }

    /**
     * Test of findTotalCalsBurnedToday method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurnedToday() {
        System.out.println("findTotalCalsBurnedToday");
                        
        ExerciseLogger instance = new ExerciseLogger();
        assertTrue(instance.findTotalCalsBurnedToday() == 0);
        
        
        exLog.addExerciseProgress(new ExerciseProgress(new Exercise("Running", "time", 5), new HKFDate(), 10, 10));
        assertTrue(exLog.findTotalCalsBurnedToday() == 10*5);
    }

    /**
     * Test of sortDate method, of class ExerciseLogger.
     */
    @Test
    public void testSortDate() {
        System.out.println("sortDate");
        ExerciseLogger instance = new ExerciseLogger();
        ArrayList<ExerciseProgress> expResult = null;
        ArrayList<ExerciseProgress> result = instance.sortDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class ExerciseLogger.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int memberID = 0;
        ExerciseLogger expResult = new ExerciseLogger();
        expResult.addExerciseProgress(new ExerciseProgress(new Exercise("t_name", "t_exercise", -1), new HKFDate("2015-04-14"), -1, -1));
        ExerciseLogger result = ExerciseLogger.find(1);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class ExerciseLogger.
     */
    @Test
    public void testPersist_0args() throws Exception {
        System.out.println("persist");
        ExerciseLogger instance = new ExerciseLogger();
        instance.persist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of persist method, of class ExerciseLogger.
     */
    @Test
    public void testPersist_ExerciseProgress() throws Exception {
        System.out.println("persist");
        ExerciseProgress exProg = null;
        ExerciseLogger instance = new ExerciseLogger();
        instance.persist(exProg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
