package com.codingmentorteam3.beans;

import com.codingmentorteam3.entities.Address;
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
public class CompanyBeanTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private CompanyBean company;
    
    public CompanyBeanTest() {
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
        company = new CompanyBean("Example company"
                             ,Mockito.mock(Address.class)
                             ,"22222222-1-33");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotViolateCompanyValidation(){
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateNameIsNotNullValidation() {
        company.setName(null);
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateNameLengthValidation() {
        company.setName("C");
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateAddressIsNotNullValidation() {
        company.setAddress(null);
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateTaxnumberPatternValidation(){
        company.setTaxNumber("22222222-1-3");
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(1, violations.size());
        assertEquals("{InvalidCompanyTaxNumber.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
    
    @Test
    public void shouldViolateLogoIsNotNullValidation() {
        company.setLogo(null);
        Set<ConstraintViolation<CompanyBean>> violations = validator.validate(company);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
}
