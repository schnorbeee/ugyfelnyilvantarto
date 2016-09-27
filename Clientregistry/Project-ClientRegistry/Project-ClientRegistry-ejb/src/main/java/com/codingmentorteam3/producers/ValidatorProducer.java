package com.codingmentorteam3.producers;

import com.codingmentorteam3.annotations.ValidatorQualifier;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Bicsak Daniel
 */
public class ValidatorProducer {

    @Produces @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
    
}