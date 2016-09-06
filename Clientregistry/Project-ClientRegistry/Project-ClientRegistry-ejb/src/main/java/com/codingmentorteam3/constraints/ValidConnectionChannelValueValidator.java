package com.codingmentorteam3.constraints;

import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.enums.ConnectionChannelType;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidConnectionChannelValueValidator 
        implements ConstraintValidator<ValidConnectionChannelValue, ConnectionChannelDTO> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{7}");
    
    @Override
    public void initialize(ValidConnectionChannelValue constraintAnnotation) {
        // Initialization
    }

    @Override
    public boolean isValid(ConnectionChannelDTO connectionChannelDTO, ConstraintValidatorContext context) {
        if(connectionChannelDTO.getType() != null && connectionChannelDTO.getValue() != null) {
            return isValidValue(connectionChannelDTO.getType(), connectionChannelDTO.getValue());
        }
        return false;
    }

    private boolean isValidValue(ConnectionChannelType type, String value) {
        switch(type) {
            case EMAIL: 
                return isValidEmail(value);
            case PHONE:
                return isValidPhone(value);
            default:
                return true;
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
    
}
