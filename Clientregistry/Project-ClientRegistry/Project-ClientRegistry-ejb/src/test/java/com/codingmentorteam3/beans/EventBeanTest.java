package com.codingmentorteam3.beans;

import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.enums.EventType;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Regulus
 */
public class EventBeanTest {

    private static ValidatorFactory vf;
    private static Validator validator;
    private EventBean event;

    public EventBeanTest() {
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

        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 100000);

        event = new EventBean("Example event", "Desc", Mockito.mock(Address.class),EventType.MEETING, startDate, endDate, Mockito.mock(Company.class));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotViolateEventValidation() {
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldViolateTitleIsNotNullValidation() {
        event.setTitle(null);
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
        );
    }

    @Test
    public void shouldViolateAddressIsNotNullValidation() {
        event.setAddress(null);
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
        );
    }

    @Test
    public void shouldViolateTitleLengthValidation() {
        event.setTitle("T");
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate()
        );
    }

    @Test
    public void shouldViolateDescriptionLengthValidation() {
        String newDesc = "ssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssssss" 
                + "ssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssssss";
        event.setDescription(newDesc);
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate()
        );
    }

    @Test
    public void shouldViolateStartDateBeforeEndDateValidation() {
        Date newStartDate = new Date();
        newStartDate.setTime(event.getEndDate().getTime() + 10000);
        event.setStartDate(newStartDate);
        Set<ConstraintViolation<EventBean>> violations
                = validator.validate(event);
        assertEquals(1, violations.size());
        assertEquals("{InvalidStartAndEndDate.message}", violations.iterator().next().getMessageTemplate()
        );
    }

}
