//package com.codingmentorteam3.beans;
//
//
//import com.codingmentorteam3.entities.Company;
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
//public class ContactPersonBeanTest {
//    
//    private static ValidatorFactory vf;
//    private static Validator validator;
//    private ContactPersonBean contactPerson;
//    
//    public ContactPersonBeanTest() {
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
//        contactPerson = new ContactPersonBean("Firstname","Lastname",Mockito.mock(Company.class));
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void shouldNotViolateContactPersonValidation() {
//        Set<ConstraintViolation<ContactPersonBean>> violations =
//                validator.validate(contactPerson);
//        assertEquals(0, violations.size());
//    }
//    
//    @Test
//    public void shouldViolateCompanyIsNotNullValidation() {
//        contactPerson.setCompany(null);
//        Set<ConstraintViolation<ContactPersonBean>> violations =
//                validator.validate(contactPerson);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}" 
//                     ,violations.iterator().next().getMessageTemplate()
//                    );
//    }
//
//
//    
//}
