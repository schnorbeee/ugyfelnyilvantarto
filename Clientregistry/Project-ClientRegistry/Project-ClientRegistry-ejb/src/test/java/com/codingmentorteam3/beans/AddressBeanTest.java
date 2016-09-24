package com.codingmentorteam3.beans;

import static org.junit.Assert.*;
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

/**
 *
 * @author Regulus
 */
public class AddressBeanTest {

    private static ValidatorFactory vf;
    private static Validator validator;
    private AddressBean address;

    public AddressBeanTest() {
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
        address = new AddressBean("Hungary"
                             ,"Test town"
                             ,"Main street"
                             ,"230"
                             ,"8900");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotViolateAddressValidation() {
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldViolateCountryIsNotNullValidation() {
        address.setCountry(null);
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateCountryLengthValidation() {
        address.setCountry("HU");
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateCityIsNotNullValidation() {
        address.setCity(null);
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateCityLengthValidation() {
        address.setCountry("H");
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateStreetIsNotNullValidation() {
        address.setStreet(null);
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateStreetLengthValidation() {
        address.setCountry("A street name above 30 character");
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Size.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateHouseNumberIsNotNullValidation() {
        address.setHouseNumber(null);
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateHouseNumberPatternValidation() {
        address.setHouseNumber("4!");
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Pattern.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateZipCodeIsNotNullValidation() {
        address.setZipCode(null);
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.NotNull.message}" 
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }

    @Test
    public void shouldViolateZipCodePatternValidation() {
        address.setZipCode("4");
        Set<ConstraintViolation<AddressBean>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        assertEquals("{javax.validation.constraints.Pattern.message}"
                     ,violations.iterator().next().getMessageTemplate()
                    );
    }
}
