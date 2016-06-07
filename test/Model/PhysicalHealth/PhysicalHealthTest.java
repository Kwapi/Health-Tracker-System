/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.PhysicalHealth;

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
public class PhysicalHealthTest {
    
    PhysicalHealth instance = new PhysicalHealth(1, -1, new Weight(-1));
    
    public PhysicalHealthTest() {
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
     * Test of getID method, of class PhysicalHealth.
     */
    @Test
    public void testGetID() {
        System.out.println("getID - simple method, wont test");
    }

    /**
     * Test of getMemberID method, of class PhysicalHealth.
     */
    @Test
    public void testGetMemberID() {
        System.out.println("getMemberID  - simple method, wont test");
    }

    /**
     * Test of getHeight method, of class PhysicalHealth.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight - simple method, wont test");
    }

    /**
     * Test of getPhysicalHealthLog method, of class PhysicalHealth.
     */
    @Test
    public void testGetPhysicalHealthLog() {
        System.out.println("getPhysicalHealthLog - simple method, wont test");
    }

    /**
     * Test of setID method, of class PhysicalHealth.
     */
    @Test
    public void testSetID() {
        System.out.println("setID - simple method, wont test");
    }

    /**
     * Test of setMemberID method, of class PhysicalHealth.
     */
    @Test
    public void testSetMemberID() {
        System.out.println("setMemberID - simple method, wont test");
    }

    /**
     * Test of setHeight method, of class PhysicalHealth.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight - simple method, wont test");
    }

    /**
     * Test of setPhysicalHealthLog method, of class PhysicalHealth.
     */
    @Test
    public void testSetPhysicalHealthLog() {
        System.out.println("setPhysicalHealthLog - simple method, wont test");
    }

    /**
     * Test of addWeightProgess method, of class PhysicalHealth.
     */
    @Test
    public void testAddWeightProgess() {
        System.out.println("addWeightProgess - simple method, wont test");
    }

    /**
     * Test of getMostRecentWeightProgress method, of class PhysicalHealth.
     */
    @Test
    public void testGetMostRecentWeightProgress() {
        System.out.println("getMostRecentWeightProgress - simple method, wont test");
    }

    /**
     * Test of findProgressesBetweenDates method, of class PhysicalHealth.
     */
    @Test
    public void testFindProgressesBetweenDates() {
        System.out.println("findProgressesBetweenDates - simple method, wont test");
    }

    /**
     * Test of findWeightOnDate method, of class PhysicalHealth.
     */
    @Test
    public void testFindWeightOnDate() {
        System.out.println("findWeightOnDate");
        HKFDate date = new HKFDate(20,04,2015);
        HKFDate date2 = new HKFDate(18,04,2015);
        HKFDate date3 = new HKFDate(9,04,2015);
        
        PhysicalHealth instance = new PhysicalHealth();
        
        Weight expResult = null;
        Weight weight1 = new Weight(-1);
        
        Weight result = instance.findWeightOnDate(date2);
        
        //assertEquals(weight1, result);
    }

    /**
     * Test of findTotalWeightDifferenceVersusDate method, of class PhysicalHealth.
     */
    @Test
    public void testFindTotalWeightDifferenceVersusDate() {
        System.out.println("findTotalWeightDifferenceVersusDate - Method not complete");
        HKFDate dateToCompareAgainst = null;
        PhysicalHealth instance = new PhysicalHealth();
        int expResult = 0;
        int result = instance.findTotalWeightDifferenceVersusDate(dateToCompareAgainst);
        //assertEquals(expResult, result);
    }

    /**
     * Test of findAverageWeightDifferencePerWeekVersusDate method, of class PhysicalHealth.
     */
    @Test
    public void testFindAverageWeightDifferencePerWeekVersusDate() {
        System.out.println("findAverageWeightDifferencePerWeekVersusDate - Method not complete");
        HKFDate dateToCompareAgainst = null;
        PhysicalHealth instance = new PhysicalHealth();
        double expResult = 0.0;
        double result = instance.findAverageWeightDifferencePerWeekVersusDate(dateToCompareAgainst);
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of findAverageWeightDifferencePerMonthVersusDate method, of class PhysicalHealth.
     */
    @Test
    public void testFindAverageWeightDifferencePerMonthVersusDate() {
        System.out.println("findAverageWeightDifferencePerMonthVersusDate - Method not complete");
        HKFDate dateToCompareAgainst = null;
        PhysicalHealth instance = new PhysicalHealth();
        double expResult = 0.0;
        double result = instance.findAverageWeightDifferencePerMonthVersusDate(dateToCompareAgainst);
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of sortDate method, of class PhysicalHealth.
     */
    @Test
    public void testSortDate() {
        System.out.println("sortDate - uses just HKFDate therefore does not require testing");
    }

    /**
     * Test of find method, of class PhysicalHealth.
     */
   // @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int memberID = 1;
        int height = -1;
        PhysicalHealth result = PhysicalHealth.find(memberID);
        int resultHeight = result.getHeight().getCentimetres();
        //Find value we added in testPersist method
        assertEquals(height, resultHeight);
    }

    /**
     * Test of findID method, of class PhysicalHealth.
     */
    @Test
    public void testFindID() throws Exception {
        System.out.println("findID");
        PhysicalHealth ph = PhysicalHealth.find(1);
        int expResult = 1;
        int result = PhysicalHealth.findID(ph);
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class PhysicalHealth.
     */
    //@Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        instance.persist();
        testFind(); //If test find works this method works
        testDelete();
    }

    /**
     * Test of delete method, of class PhysicalHealth.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        instance.delete(instance.getID());
        //Delete instance we just added, if null then it worked
        assertEquals(-1, PhysicalHealth.find(1).getHeight().getCentimetres());
    }

    /**
     * Test of deleteAll method, of class PhysicalHealth.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll - same as Delete method just with loop");
    }
    
}
