package com.codingmentorteam3.validators;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author istvan.mosonyi
 */
@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {
    
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*[a-z].*[A-Z].*[0-9]|[=\\+<>\\.,].*");
    private static final Integer PASSWORD_LENGTH = 6;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = value.toString();
        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
        
        if(!passwordMatches(value)) {
            throw new ValidatorException(getWrongPasswordFacesMessage());
        }
        if(!password.equals(confirmPassword)) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(getWrongPasswordFacesMessage());
        }
        
    }
    
    private boolean passwordMatches(Object value) {
        return hasValidLength(value) && matchesPasswordPattern(value);
    }

    private boolean hasValidLength(Object value) {
        return value.toString().length() >= PASSWORD_LENGTH;
    }
    
    private boolean matchesPasswordPattern(Object value) {
        return PASSWORD_PATTERN.matcher(value.toString()).matches();
    }
    
    private FacesMessage getWrongPasswordFacesMessage() {
        FacesMessage msg = new FacesMessage("Password validation failed. Invalid password format.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }
    
}
