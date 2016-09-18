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
@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(!matchesEmailPattern(value)) {
            throw new ValidatorException(getWrongEmailFacesMessage());
        }
    }
    
    private boolean matchesEmailPattern(Object value) {
        return EMAIL_PATTERN.matcher(value.toString()).matches();
    }
    
    private FacesMessage getWrongEmailFacesMessage() {
        FacesMessage msg = new FacesMessage("Invalid E-mail format, please enter a valid e-mail address.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }
    
}
