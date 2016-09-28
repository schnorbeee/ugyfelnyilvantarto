//package com.codingmentorteam3.beans;
//
//import com.codingmentorteam3.entities.Person;
//import com.codingmentorteam3.enums.ConnectionChannelType;
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
//public class ConnectionChannelBeanTest {
//
//    private static ValidatorFactory vf;
//    private static Validator validator;
//    private ConnectionChannelBean connectionChannel;
//
//    public ConnectionChannelBeanTest() {
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
//        connectionChannel = new ConnectionChannelBean(
//                ConnectionChannelType.EMAIL, "example@example.com", Mockito.mock(Person.class)
//        );
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void shouldNotViolateConnectionChannelValidation() {
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(0, violations.size());
//    }
//
//    @Test
//    public void shouldViolateOwnerIsNotNullValidation() {
//        connectionChannel.setOwner(null);
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//    @Test
//    public void shouldViolateTypeIsNotNullValidation() {
//        connectionChannel.setType(null);
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(2, violations.size());
//
//        boolean notNullViolated = false;
//        boolean valueViolated = false;
//
//        for (ConstraintViolation<ConnectionChannelBean> violation : violations) {
//            if(violation.getMessageTemplate().equals("{javax.validation.constraints.NotNull.message}"))
//            {
//                notNullViolated = true;
//            }
//            
//            if(violation.getMessageTemplate().equals("{InvalidConnectionChannelValue.message}"))
//            {
//                valueViolated = true;
//            }
//        }
//        
//        if (notNullViolated == true) {
//            assertEquals(valueViolated, notNullViolated);
//        }
//    }
//
//    @Test
//    public void shouldViolateValueIsNotNullValidation() {
//        connectionChannel.setValue(null);
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(2, violations.size());
//        
//        boolean notNullViolated = false;
//        boolean valueViolated = false;
//
//        for (ConstraintViolation<ConnectionChannelBean> violation : violations) {
//            if(violation.getMessageTemplate().equals("{javax.validation.constraints.NotNull.message}"))
//            {
//                notNullViolated = true;
//            }
//            
//            if(violation.getMessageTemplate().equals("{InvalidConnectionChannelValue.message}"))
//            {
//                valueViolated = true;
//            }
//        }
//        
//        if (notNullViolated == true) {
//            assertEquals(valueViolated, notNullViolated);
//        }
//    }
//    
//    @Test
//    public void shouldViolateEmailPatternValidation(){
//        connectionChannel.setValue("example?@gmail.com");
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(1, violations.size());
//        assertEquals("{InvalidConnectionChannelValue.message}"
//                     ,violations.iterator().next().getMessageTemplate()
//                    );
//    }
//    
//    @Test
//    public void shouldViolatePhonePatternValidation(){
//        connectionChannel.setType(ConnectionChannelType.PHONE);
//        connectionChannel.setValue("3630123788");
//        Set<ConstraintViolation<ConnectionChannelBean>> violations
//                = validator.validate(connectionChannel);
//        assertEquals(1, violations.size());
//        assertEquals("{InvalidConnectionChannelValue.message}"
//                     ,violations.iterator().next().getMessageTemplate()
//                    );
//    }
//
//}
