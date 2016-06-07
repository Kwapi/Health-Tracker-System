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
 * @author xmw13bzu
 */
public class MemberTest {
    
    public MemberTest() {
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
     * Test of find method, of class Member.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        String email = "t_email";
        String password = "t_pass";
        Member expResult = new Member(1, "t_user", "t_pass", "t_email",
            "t_fname", "t_sname");
        Member result = Member.find(email, password);
        
        System.out.println("expresult " + expResult + "\nresult" + result);
        
        assertEquals(expResult, result);
        
  
        // TODO review the generated test code and remove the default call to fail.
        //fail("testFind() failed");
    }

    /**
     * Test of findID method, of class Member.
     */
    @Test
    public void testFindID() throws Exception {
        System.out.println("findID");
        Member member = new Member(-1, "t_user", "t_pass", "t_email",
            "t_fname", "t_sname");
        int expResult = 1;
        int result = Member.findID(member);
        
        System.out.println("expresult " + expResult + "\nresult" + result + "\nmember" + member);
        
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        //fail("testFindID() failed");
    }

    /**
     * Test of persist method, of class Member.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        Member instance = new Member("t1 user", "t1 pass", "t1 email",
            "t1 fname", "t1 sname");
        instance.persist();
        
        Member instance2 = Member.find("t1 email", "t1 pass");
        instance.setUserID(instance2.getUserID());
     
        assertEquals(instance, instance2);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("testPersist() failed");
    }
    
    /**
     * Test of equals method, of class Member.
     */
    @Test
    public void testEquals() throws Exception {
        System.out.println("equals");
        Member instance = new Member("t1 user", "t1 pass", "t1 email",
            "t1 fname", "t1 sname");
        Member instance2 = new Member("t1 user", "t1 pass", "t1 email",
            "t1 fname", "t1 sname");
        
        assertEquals(instance, instance2);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("testPersist() failed");
    }
    
}
