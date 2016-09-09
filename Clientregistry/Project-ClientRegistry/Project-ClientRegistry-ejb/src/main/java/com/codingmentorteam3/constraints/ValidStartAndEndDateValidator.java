package com.codingmentorteam3.constraints;

import com.codingmentorteam3.dtos.EventDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidStartAndEndDateValidator 
        implements ConstraintValidator<ValidStartAndEndDate, EventDTO> {

    @Override
    public void initialize(ValidStartAndEndDate constraintAnnotation) {
        // Initialize
    }

    @Override
    public boolean isValid(EventDTO eventDTO, ConstraintValidatorContext context) {
       if(eventDTO.getEndDate() != null) {
           return eventDTO.getStartDate().before(eventDTO.getEndDate());
       }
       return true;
    }
    
}
