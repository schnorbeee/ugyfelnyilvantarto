package com.codingmentorteam3.constraints;

import com.codingmentorteam3.beans.EventBean;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidStartAndEndDateValidator 
        implements ConstraintValidator<ValidStartAndEndDate, EventBean> {

    @Override
    public void initialize(ValidStartAndEndDate constraintAnnotation) {
        // Initialize
    }

    @Override
    public boolean isValid(EventBean eventBean, ConstraintValidatorContext context) {
       if(eventBean.getEndDate() != null) {
           return eventBean.getStartDate().before(eventBean.getEndDate());
       }
       return true;
    }
    
}
