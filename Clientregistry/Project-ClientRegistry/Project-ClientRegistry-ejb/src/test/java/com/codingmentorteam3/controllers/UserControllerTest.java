/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.dtos.RoleDTO;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.ConnectionChannelType;
import com.codingmentorteam3.enums.RankType;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.util.UtilBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author norbeee
 */
public class UserControllerTest {

    UserController userController = new UserController();
    
    UtilBean utilBean = new UtilBean();

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
     *
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
     *
     * @throws java.lang.Exception
     */
    /*@Test
    public void testRegistration() throws Exception {
        LOG.info("registration");
        
        UserBean newUser = new UserBean();
        newUser.setFirstName("Nagy");
        newUser.setLastName("Bela");
        newUser.setUsername("Bela1234");
        newUser.setPassword("PassWord123");
        User regUser = new User(newUser);
        regUser.setPassword(utilBean.sha256coding(regUser.getPassword()));
        UserDTO userDto = new UserDTO(regUser);
        
        ConnectionChannelBean newChannel = new ConnectionChannelBean(ConnectionChannelType.EMAIL, "email@email.hu", regUser);
        ConnectionChannel regChannel = new ConnectionChannel(newChannel);
        ConnectionChannelDTO connectionChannelDto = new ConnectionChannelDTO(regChannel);
        
        RoleBean newRoleBean = new RoleBean(RoleType.VISITOR, regUser);
        Role newRole = new Role(newRoleBean);
        RoleDTO roleDto = new RoleDTO(newRole);
        
        Response expResult = Response.status(Response.Status.CREATED).entity(userDto).entity(connectionChannelDto).entity(roleDto).type(MediaType.APPLICATION_JSON).build();
        Response result = userController.registrate(newUser, newChannel);
        assertEquals(expResult, result);
    }
    */
}
