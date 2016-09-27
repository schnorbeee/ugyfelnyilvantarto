package com.codingmentorteam3.beans;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Regulus
 */
public class PersonBeanTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private PersonBean person;
    
    public PersonBeanTest() {
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
        person = new PersonBean("Example","Person");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void shouldNotViolatePersonValidation(){
         Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateFirstNameIsNotNullValidation(){
        person.setFirstName(null);
        Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateLastNameIsNotNullValidation(){
        person.setLastName(null);
        Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateAvatarIsNotNullValidation(){
        person.setAvatar(null);
        Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateFirstNameLengthValidation(){
        person.setFirstName("N");
        Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateLastNameLengthValidation(){
        person.setLastName("N");
        Set<ConstraintViolation<PersonBean>> violations =
                validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
}
