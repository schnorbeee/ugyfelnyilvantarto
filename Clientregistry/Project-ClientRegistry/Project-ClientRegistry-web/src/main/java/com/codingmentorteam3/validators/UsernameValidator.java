package com.codingmentorteam3.validators;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author istvan.mosonyi
 */
@FacesValidator("UsernameValidator")
public class UsernameValidator implements Validator {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("\\w{6,}");
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(!usernameMatchesPattern(value)) {
            throw new ValidatorException(getWrongUsernameFacesMessage());
        }
    }
    
    private boolean usernameMatchesPattern(Object value) {
        return USERNAME_PATTERN.matcher(value.toString()).matches();
    }
    
    private FacesMessage getWrongUsernameFacesMessage() {
        FacesMessage msg = new FacesMessage("Username format is invalid. Please enter a username with a minimum length of 6 containing letters and numbers.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }
    
}
