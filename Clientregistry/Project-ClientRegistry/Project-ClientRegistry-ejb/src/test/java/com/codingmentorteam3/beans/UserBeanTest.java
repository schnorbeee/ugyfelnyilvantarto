/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingmentorteam3.beans;

import com.codingmentorteam3.enums.PositionType;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Regulus
 */
public class UserBeanTest {

    private static ValidatorFactory vf;
    private static Validator validator;
    private UserBean user;

    public UserBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        user = new UserBean("Username", "Pass+word1", "Firstname", "Lastname", PositionType.EMPLOYEE, "default");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotViolateUserValidation() {
        Set<ConstraintViolation<UserBean>> violations
                = validator.validate(user);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateUsernameIsNotNullValidation(){
        user.setUsername(null);
        Set<ConstraintViolation<UserBean>> violations =
                validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("{InvalidUsername.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolatePasswordIsNotNullValidation(){
        user.setPassword(null);
        Set<ConstraintViolation<UserBean>> violations =
                validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("{InvalidPassword.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateUsernamePatternValidation(){
        user.setUsername("usr");
        Set<ConstraintViolation<UserBean>> violations =
                validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("{InvalidUsername.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolatePasswordPatternValidation(){
        user.setPassword("pass");
        Set<ConstraintViolation<UserBean>> violations =
                validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("{InvalidPassword.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
}
