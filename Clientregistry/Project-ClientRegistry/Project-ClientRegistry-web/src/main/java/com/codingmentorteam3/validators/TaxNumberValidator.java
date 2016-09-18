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
@FacesValidator("TaxNumberValidator")
public class TaxNumberValidator implements Validator {
    
    private static final Pattern TAX_PATTERN = Pattern.compile("\\d{8}-\\d{1}-\\d{2}");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(!matchesTaxPattern(value)) {
            throw new ValidatorException(getWrongTaxNumberFacesMessage());
        }
    }

    private boolean matchesTaxPattern(Object value) {
        return TAX_PATTERN.matcher(value.toString()).matches();
    }

    private FacesMessage getWrongTaxNumberFacesMessage() {
        FacesMessage msg = new FacesMessage("Invalid tax-number format. Please enter a tax-number matching the pattern: XXXXXXXX-X-XX");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }

}
