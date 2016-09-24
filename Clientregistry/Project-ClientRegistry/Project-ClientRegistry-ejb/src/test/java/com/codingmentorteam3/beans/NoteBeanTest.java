/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class NoteBeanTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private NoteBean note;
    
    public NoteBeanTest() {
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
        note = new NoteBean("Label","Comment",Mockito.mock(User.class),Mockito.mock(Event.class));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void shouldNotViolateNoteValidation(){
         Set<ConstraintViolation<NoteBean>> violations =
                validator.validate(note);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateUserIsNotNullValidation(){
        note.setUser(null);
        Set<ConstraintViolation<NoteBean>> violations =
                validator.validate(note);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateEventIsNotNullValidation(){
        note.setEvent(null);
        Set<ConstraintViolation<NoteBean>> violations =
                validator.validate(note);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateContentIsNotNullValidation(){
        note.setContent(null);
        Set<ConstraintViolation<NoteBean>> violations =
                validator.validate(note);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateLabelMaxLengthValidation(){
        note.setLabel("MaximumLengthOfTheLabelViolated");
        Set<ConstraintViolation<NoteBean>> violations =
                validator.validate(note);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
}
