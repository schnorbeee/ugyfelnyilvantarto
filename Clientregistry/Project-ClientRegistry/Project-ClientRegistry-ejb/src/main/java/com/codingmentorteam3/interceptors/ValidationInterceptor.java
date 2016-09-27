package com.codingmentorteam3.interceptors;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.annotations.ValidatorQualifier;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

/**
 *
 * @author istvan.mosonyi
 */
@Interceptor
@BeanValidation
public class ValidationInterceptor {
    
    @Inject @ValidatorQualifier
    private Validator validator;
    
    @AroundInvoke
    public Object validateBeans(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        for(Object p : parameters) {
            if(p.getClass().isAnnotationPresent(Validate.class)) {
                validateBean(p);
            }
        }
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if(!violations.isEmpty()) {
            String errorMessage = getErrorMessage(violations);
            throw new ValidationException(errorMessage);
        }
    }
    
    private String getErrorMessage(Set<ConstraintViolation<Object>> violations) {
        StringBuilder errorMessage = new StringBuilder();
        for(ConstraintViolation<Object> error : violations) {
            errorMessage.append("Validation error: ")
                        .append(error.getMessage())
                        .append(" - property: ")
                        .append(error.getPropertyPath().toString())
                        .append(".");
        }
        return errorMessage.toString();
    }
    
}
