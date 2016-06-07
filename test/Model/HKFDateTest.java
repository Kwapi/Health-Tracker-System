/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

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
public class HKFDateTest {
        
    public HKFDateTest() {
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
     * Test of setDay method, of class HKFDate.
     */
    @Test
    public void testSetDay() {
        System.out.println("setDay");
        int day = -3;
        HKFDate instance = new HKFDate();
        instance.setDay(day);        
    }

    /**
     * Test of setMonth method, of class HKFDate.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        int month = 13;
        HKFDate instance = new HKFDate();
        instance.setMonth(month);
    }

    /**
     * Test of setYear method, of class HKFDate.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2013;
        HKFDate instance = new HKFDate();
        instance.setYear(year);
    }
    /**
     * Test of setYear method, of class HKFDate.
     */
    @Test
    public void testSetHour() {
        System.out.println("setYear");
        int hour = 25;
        HKFDate instance = new HKFDate();
        instance.setHours(hour);
        
    }
    
    /**
     * Test of setYear method, of class HKFDate.
     */
    @Test
    public void testSetMinute() {
        System.out.println("setYear");
        int minutes = 65;
        HKFDate instance = new HKFDate();
        instance.setMinutes(minutes);
        
    }

    /**
     * Test of monthSize method, of class HKFDate.
     */
    @Test
    public void testMonthSize() {
        System.out.println("monthSize");
        HKFDate instance = new HKFDate(1,1,1111);
        int expResult = 31;
        int result = instance.monthSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class HKFDate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HKFDate instance1 = new HKFDate(2,3,2015);
        String expResult1 = "2015-03-02";
        String result1 = instance1.toString();
        
        String expResult2 = "2015-05-27";
        HKFDate instance2 = new HKFDate(expResult2);
        String result2 = instance2.toString();
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of compareTo method, of class HKFDate.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        HKFDate equal = new HKFDate(5,3,2014);
        HKFDate greater = new HKFDate(15,3,2014);
        HKFDate lessThan = new HKFDate(15,2,2014);
        HKFDate instance = new HKFDate(5,3,2014);
        
        int equalResult = instance.compareTo(equal);
        int gtResult = instance.compareTo(greater);
        int ltResult = instance.compareTo(lessThan);
        
        assertEquals(0, equalResult);
        assertEquals(1, gtResult);
        assertEquals(-1, ltResult);
    }

    /**
     * Test of splitString method, of class HKFDate.
     */
    @Test
    public void testSplitString() {
        System.out.println("splitString");
        String dateString = "2015-08-15";
        HKFDate instance = new HKFDate(dateString);
        
        assertEquals(2015, instance.getYear());
        assertEquals(8, instance.getMonth());
        assertEquals(15, instance.getDay());
        
        
    }

    /**
     * Test of equals method, of class HKFDate.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new HKFDate(20,9,2015);
        HKFDate instance = new HKFDate(20,9,2015);
        
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of diffDay method, of class HKFDate.
     */
    @Test
    public void testDiffDay() {
        System.out.println("diffDay");
        HKFDate date = null;
        HKFDate instance = new HKFDate();
        int expResult = 0;
        int result = instance.diffDay(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diffMin method, of class HKFDate.
     */
    @Test
    public void testDiffMin() {
        System.out.println("diffMin");
        HKFDate date = null;
        HKFDate instance = new HKFDate();
        int expResult = 0;
        int result = instance.diffMin(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDate method, of class HKFDate.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        int minutes = 12*24*60;
        HKFDate instance = new HKFDate();
        
        HKFDate expResult = new HKFDate(30,4,2015);
        HKFDate result = instance.getEndDate(minutes);
        
        assertEquals(expResult, result);
    }
    
}
