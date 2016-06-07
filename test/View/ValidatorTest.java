/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JamesL
 */
public class ValidatorTest {
    
    public ValidatorTest() {
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
     * Test of isValid method, of class Validator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Validator instance = new Validator();
        boolean expResult = true;
        boolean result = instance.isValid();
        assertTrue(expResult == result);
    }

    /**
     * Test of setErrMsg and getErrMsg methods, of class Validator.
     */
    @Test
    public void testSetANDGetErrMsg() {
        System.out.println("setANDGetErrMsg");
        String newMsg = "Invalid test hurrdurr";
        Validator instance = new Validator();
        instance.setErrMsg(newMsg);
        String result = instance.getErrMsg();
        assertEquals(newMsg, result);
    }

    /**
     * Test of clearErrMsg method, of class Validator.
     */
    @Test
    public void testClearErrMsg() {
        System.out.println("clearErrMsg");
        Validator instance = new Validator();
        instance.setErrMsg("aaaaaaa");
        instance.clearErrMsg();
        assertEquals(instance.getErrMsg(), "");
    }

    /**
     * Test of appendErrMsg method, of class Validator.
     */
    @Test
    public void testAppendErrMsg() {
        System.out.println("appendErrMsg");
        String newMsg1 = "invalid case 1";
        String newMsg2 = "invalid case 2";
        Validator instance = new Validator();
        instance.appendErrMsg(newMsg1);
        assertEquals(instance.getErrMsg(), newMsg1);
        instance.appendErrMsg(newMsg2);
        assertEquals(instance.getErrMsg(), newMsg1 + "\n" + newMsg2);
    }

    /**
     * Test of isEmpty method, of class Validator.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        String value1 = "asd";
        String value2 = "";
        String value3 = null;
        Validator instance = new Validator();
        assertFalse(instance.isEmpty(value1));
        assertTrue(instance.isEmpty(value2));
        assertTrue(instance.isEmpty(value3));

    }

    /**
     * Test of isWithinRange method, of class Validator.
     */
    @Test
    public void testIsWithinRange() {
        System.out.println("isWithinRange");
        int value1 = -1;
        int value2 = 0;
        int value3 = 5;
        int value4 = 10;
        int value5 = 11;
        
        int low = 0;
        int high = 10;
        
        Validator instance = new Validator();

        assertFalse(instance.isWithinRange(value1, low, high));
        assertTrue(instance.isWithinRange(value2, low, high));
        assertTrue(instance.isWithinRange(value3, low, high));
        assertTrue(instance.isWithinRange(value4, low, high));
        assertFalse(instance.isWithinRange(value5, low, high));
    }

    /**
     * Test of validateEmail method, of class Validator.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String errMsg = "";
        String email1 = "asd";
        String email2 = "asd@gmail.com";
        String email3 = "asd@aa";
        String email4 = "123AS@D:ASD'asd';lm@gmail.com";
        String email5 = "asd.com";
        String email6 = null;
        
        Validator instance = new Validator();

        assertFalse(instance.validateEmail(errMsg, email1));
        assertTrue(instance.validateEmail(errMsg, email2));
        assertFalse(instance.validateEmail(errMsg, email3));
        assertFalse(instance.validateEmail(errMsg, email4));
        assertFalse(instance.validateEmail(errMsg, email5));
        assertFalse(instance.validateEmail(errMsg, email6));
    }

    /**
     * Test of validateDate method, of class Validator.
     */
    @Test
    public void testValidateDate() {
        System.out.println("validateDate");
        String errMsg = "a";
        String dateString1 = "2015-04-15";
        String dateString2 = "0000-04-15";
        String dateString3 = "15-04-2015";
        String dateString4 = "2015-04-15a";
        String dateString5 = "asdasd";
        String dateString6 = null;
        
        Validator instance = new Validator();

        assertTrue(instance.validateDate(errMsg, dateString1));
        assertFalse(instance.validateDate(errMsg, dateString2));   
        assertFalse(instance.validateDate(errMsg, dateString3));
        assertFalse(instance.validateDate(errMsg, dateString4));
        assertFalse(instance.validateDate(errMsg, dateString5));
        assertFalse(instance.validateDate(errMsg, dateString6));
    }

    /**
     * Test of validateUsername method, of class Validator.
     */
    @Test
    public void testValidateUsername() {
        System.out.println("validateUsername");
               
        String errMsg = "a";
        String username1 = "asdasd1";
        String username2 = "d";
        String username3 = "asd_asd";
        String username4 = "_asd1123";
        String username5 = "asdasd@:ASD";
        String username6 = "averylongusername";
        String username7 = null;
        
        Validator instance = new Validator();

        assertTrue(instance.validateUsername(errMsg, username1));
        assertFalse(instance.validateUsername(errMsg, username2));   
        assertTrue(instance.validateUsername(errMsg, username3));
        assertFalse(instance.validateUsername(errMsg, username4));
        assertFalse(instance.validateUsername(errMsg, username5));
        assertFalse(instance.validateUsername(errMsg, username6));
        assertFalse(instance.validateUsername(errMsg, username7));
    }

    /**
     * Test of validateName method, of class Validator.
     */
    @Test
    public void testValidateName() {
        System.out.println("validateName");
               
        String errMsg = "a";
        String name1 = "asdasd asdasd";
        String name2 = "";
        String name3 = "asd_asd";
        String name4 = "asd1123";
        String name5 = "asdasd@:ASD";
        String name6 = "averylonghuseas dasdasdasdrname";
        String name7 = null;
        
        Validator instance = new Validator();

        assertTrue(instance.validateName(errMsg, name1));
        assertFalse(instance.validateName(errMsg, name2));   
        assertFalse(instance.validateName(errMsg, name3));
        assertFalse(instance.validateName(errMsg, name4));
        assertFalse(instance.validateName(errMsg, name5));
        assertTrue(instance.validateName(errMsg, name6));
        assertFalse(instance.validateName(errMsg, name7));
    }

    /**
     * Test of validatePassword method, of class Validator.
     */
    @Test
    public void testValidatePassword() {
        System.out.println("validatePassword");
               
        String errMsg = "a";
        String password1 = "asdasdasdasd";
        String password2 = "";
        String password3 = "asd_asd";
        String password4 = "asd1123";
        String password5 = "asdasd@:ASD";
        String password6 = "averylonghuseasdasdasdasdrpassword";
        String password7 = null;
        
        Validator instance = new Validator();

        assertTrue(instance.validatePassword(errMsg, password1));
        assertFalse(instance.validatePassword(errMsg, password2));   
        assertTrue(instance.validatePassword(errMsg, password3));
        assertTrue(instance.validatePassword(errMsg, password4));
        assertFalse(instance.validatePassword(errMsg, password5));
        assertFalse(instance.validatePassword(errMsg, password6));
        assertFalse(instance.validatePassword(errMsg, password7));
    }

    /**
     * Test of validateInt method, of class Validator.
     */
    @Test
    public void testValidateInt() {
        System.out.println("validateInt");
        String errMsg = "";
        String number1 = "0";
        String number2 = "123asd";
        String number3 = "123123123";
        String number4 = "12 asd";
        String number5 = "-2";
        
        Validator instance = new Validator();
        
        int expResult1 = 0;
        int expResult2 = 0;
        int expResult3 = 123123123;
        int expResult4 = 0;
        int expResult5 = -2;
        
        int result1 = instance.validateInt(errMsg, number1);
        int result2 = instance.validateInt(errMsg, number2);
        int result3 = instance.validateInt(errMsg, number3);
        int result4 = instance.validateInt(errMsg, number4);
        int result5 = instance.validateInt(errMsg, number5);
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
    }

    /**
     * Test of validatePositiveInt method, of class Validator.
     */
    @Test
    public void testValidatePositiveInt() {
        System.out.println("validatePositiveInt");
        String errMsg = "";
        String number1 = "0";
        String number2 = "123asd";
        String number3 = "123123123";
        String number4 = "12 asd";
        String number5 = "-2";
        
        Validator instance = new Validator();
        
        int expResult1 = 0;
        int expResult2 = 0;
        int expResult3 = 123123123;
        int expResult4 = 0;
        int expResult5 = 0;
        
        int result1 = instance.validatePositiveInt(errMsg, number1);
        int result2 = instance.validatePositiveInt(errMsg, number2);
        int result3 = instance.validatePositiveInt(errMsg, number3);
        int result4 = instance.validatePositiveInt(errMsg, number4);
        int result5 = instance.validatePositiveInt(errMsg, number5);
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
    }

    /**
     * Test of validateIntWithinRange method, of class Validator.
     */
    @Test
    public void testValidateIntWithinRange() {
        System.out.println("validateIntWithinRange");
       
        String errMsg = "";
        String number1 = "1";
        String number2 = "5";
        String number3 = "123asd";
        String number4 = "123123123";
        String number5 = "12 asd";
        String number6 = "-2";
        
        int low = 0, high = 5;
        
        Validator instance = new Validator();
        
        int expResult1 = 1;
        int expResult2 = 5;
        int expResult3 = 0;
        int expResult4 = 0;
        int expResult5 = 0;
        int expResult6 = 0;
        
        int result1 = instance.validateIntWithinRange(errMsg, number1, low, high);
        int result2 = instance.validateIntWithinRange(errMsg, number2, low, high);
        int result3 = instance.validateIntWithinRange(errMsg, number3, low, high);
        int result4 = instance.validateIntWithinRange(errMsg, number4, low, high);
        int result5 = instance.validateIntWithinRange(errMsg, number5, low, high);
        int result6 = instance.validateIntWithinRange(errMsg, number6, low, high);
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
        assertEquals(expResult6, result6);
    }
    
}
