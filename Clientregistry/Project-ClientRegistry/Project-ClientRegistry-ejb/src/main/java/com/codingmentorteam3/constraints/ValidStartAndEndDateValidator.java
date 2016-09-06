package com.codingmentorteam3.constraints;

import com.codingmentorteam3.dtos.EventDTO;
import java.util.Date;
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
        if(eventDTO.getStartDate() == null) {
            return false;
        } else if(eventDTO.getEndDate() != null) {
            return isEndDateLaterThanStartDate(eventDTO.getStartDate(), eventDTO.getEndDate());
        }
        return false;
    }

    private boolean isEndDateLaterThanStartDate(Date startDate, Date endDate) {
        return startDate.before(endDate);
    }
    
}
