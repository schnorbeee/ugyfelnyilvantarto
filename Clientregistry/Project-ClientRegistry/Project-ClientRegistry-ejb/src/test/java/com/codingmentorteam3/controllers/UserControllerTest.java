/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RankType;
import com.codingmentorteam3.util.UtilBean;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author norbeee
 */
public class UserControllerTest {

    private static final Logger LOG = Logger.getLogger(UserControllerTest.class.getName());
    
    
    
    public UserControllerTest() {
        //Default constructor
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
     * Test of login method, of class UserController.
     * @throws java.lang.Exception
     */
    /*@Test
    public void testLogin() throws Exception {
        System.out.println("login");
        SecurityContext ctx = null;
        HttpServletRequest request = null;
        User user = null;
        UserController instance = new UserController();
        Response expResult = null;
        Response result = instance.login(ctx, request, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class UserController.
     */
    /*@Test
    public void testLogout() throws Exception {
        System.out.println("logout");
        SecurityContext ctx = null;
        HttpServletRequest request = null;
        UserDTO dto = null;
        UserController instance = new UserController();
        Response expResult = null;
        Response result = instance.logout(ctx, request, dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of registration method, of class UserController.
     * @throws java.lang.Exception
     */
    @Test
    public void testRegistration() throws Exception {
        LOG.info("registration");
        UserController userController = new UserController();
        User regUser = new User();
        regUser.setFirstName("Nagy");
        regUser.setLastName("Bela");
        regUser.setUsername("bela123");
        regUser.setPassword("Password123");
        regUser.setRank(RankType.VISITOR);
        regUser.setAvatar("avatar");
        
        Response expResult = Response.ok(new UserDTO(regUser)).build();
        Response result = userController.registration(regUser);
        assertEquals(expResult, result);
    }
    
}
