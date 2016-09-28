///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.codingmentorteam3.beans;
//
//import com.codingmentorteam3.entities.User;
//import java.util.Set;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.mockito.Mockito;
//
///**
// *
// * @author Regulus
// */
//public class RoleBeanTest {
//    
//    private static ValidatorFactory vf;
//    private static Validator validator;
//    private RoleBean role;
//    
//    public RoleBeanTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//        vf = Validation.buildDefaultValidatorFactory();
//        validator = vf.getValidator();
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        role = new RoleBean(Mockito.mock(User.class));
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void shouldNotRoleValidation(){
//         Set<ConstraintViolation<RoleBean>> violations =
//                validator.validate(role);
//        assertEquals(0, violations.size());
//    }
//    
//    @Test
//    public void shouldViolateUserIsNotNullValidation(){
//        role.setUser(null);
//        Set<ConstraintViolation<RoleBean>> violations =
//                validator.validate(role);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}" 
//                     ,violations.iterator().next().getMessageTemplate()
//                    );
//    }
//    
//    @Test
//    public void shouldViolateRoleTypeIsNotNullValidation(){
//        role.setType(null);
//        Set<ConstraintViolation<RoleBean>> violations =
//                validator.validate(role);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}" 
//                     ,violations.iterator().next().getMessageTemplate()
//                    );
//    }
//    
//}
