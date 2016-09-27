package com.codingmentorteam3.constraints;

import com.codingmentorteam3.beans.ProjectBean;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidDeadlineValidator 
        implements ConstraintValidator<ValidDeadline, ProjectBean> {

    @Override
    public void initialize(ValidDeadline constraintAnnotation) {
        // Initialize
    }

    @Override
    public boolean isValid(ProjectBean projectBean, ConstraintValidatorContext context) {
        if(projectBean.getStartDate() != null && projectBean.getDeadline() != null) {
            return projectBean.getStartDate().before(projectBean.getDeadline());
        }
        return true;
    }
    
}
