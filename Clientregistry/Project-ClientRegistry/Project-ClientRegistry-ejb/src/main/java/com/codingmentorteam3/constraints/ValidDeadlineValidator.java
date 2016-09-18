package com.codingmentorteam3.constraints;

import com.codingmentorteam3.dtos.ProjectDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidDeadlineValidator 
        implements ConstraintValidator<ValidDeadline, ProjectDTO> {

    @Override
    public void initialize(ValidDeadline constraintAnnotation) {
        // Initialize
    }

    @Override
    public boolean isValid(ProjectDTO projectDTO, ConstraintValidatorContext context) {
        if(projectDTO.getStartDate() != null && projectDTO.getDeadline() != null) {
            return projectDTO.getStartDate().before(projectDTO.getDeadline());
        }
        return true;
    }
    
}
