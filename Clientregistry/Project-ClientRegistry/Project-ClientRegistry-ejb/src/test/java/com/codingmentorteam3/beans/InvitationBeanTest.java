package com.codingmentorteam3.beans;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.User;
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
public class InvitationBeanTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private InvitationBean invitation;
    
    public InvitationBeanTest() {
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
        invitation = new InvitationBean(Mockito.mock(User.class)
                                       ,Mockito.mock(User.class)
                                       ,Mockito.mock(Event.class)
                                       ,"Message");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotViolateInvitationValidation(){
         Set<ConstraintViolation<InvitationBean>> violations =
                validator.validate(invitation);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateSenderIsNotNullValidation(){
        invitation.setSender(null);
        Set<ConstraintViolation<InvitationBean>> violations =
                validator.validate(invitation);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateReceiverIsNotNullValidation(){
        invitation.setReceiver(null);
        Set<ConstraintViolation<InvitationBean>> violations =
                validator.validate(invitation);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );

    }
    
    @Test
    public void shouldViolateEventIsNotNullValidation(){
        invitation.setEvent(null);
        Set<ConstraintViolation<InvitationBean>> violations =
                validator.validate(invitation);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );

    }
    
    @Test
    public void shouldViolateFeedbackIsNotNullValidation(){
        invitation.setFeedback(null);
        Set<ConstraintViolation<InvitationBean>> violations =
                validator.validate(invitation);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    
}
