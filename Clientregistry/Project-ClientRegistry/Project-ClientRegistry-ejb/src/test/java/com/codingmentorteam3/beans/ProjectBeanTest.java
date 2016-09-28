//package com.codingmentorteam3.beans;
//
//import com.codingmentorteam3.enums.StatusType;
//import java.util.Date;
//import java.util.Set;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Regulus
// */
//public class ProjectBeanTest {
//
//    private static ValidatorFactory vf;
//    private static Validator validator;
//    private ProjectBean project;
//
//    public ProjectBeanTest() {
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
//        Date startDate = new Date();
//        Date endDate = new Date();
//        endDate.setTime(startDate.getTime() + 10000000);
//        project = new ProjectBean("Proj1", "Desc", StatusType.BEGINNING, startDate, endDate);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void shouldNotViolateProjectValidation() {
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(0, violations.size());
//    }
//
//    @Test
//    public void shouldViolateNameIsNotNullValidation() {
//        project.setName(null);
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//    @Test
//    public void shouldViolateDescriptionIsNotNullValidation() {
//        project.setDescription(null);
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//    @Test
//    public void shouldViolateStatusIsNotNullValidation() {
//        project.setStatus(null);
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//    @Test
//    public void shouldViolateNameLengthValidation() {
//        project.setName("MaximumLengthOfTheProjectNameViolated");
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(1, violations.size());
//        assertEquals("{javax.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//    public void shouldViolateStartDateBeforeEndDateValidation() {
//        Date newStartDate = new Date();
//        newStartDate.setTime(project.getDeadline().getTime() + 10000);
//        project.setStartDate(newStartDate);
//        Set<ConstraintViolation<ProjectBean>> violations
//                = validator.validate(project);
//        assertEquals(1, violations.size());
//        assertEquals("{InvalidDeadline.message}", violations.iterator().next().getMessageTemplate()
//        );
//    }
//
//}
