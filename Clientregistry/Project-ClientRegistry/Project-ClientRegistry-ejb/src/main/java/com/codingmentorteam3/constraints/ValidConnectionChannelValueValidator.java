package com.codingmentorteam3.constraints;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.enums.ConnectionChannelType;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author istvan.mosonyi
 */
public class ValidConnectionChannelValueValidator implements 
     ConstraintValidator<ValidConnectionChannelValue, ConnectionChannelBean> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public void initialize(ValidConnectionChannelValue constraintAnnotation) {
        // Initialization
    }

    @Override
    public boolean isValid(ConnectionChannelBean connectionChannelBean, ConstraintValidatorContext context) {
        
        ConnectionChannelType type = connectionChannelBean.getType();
        String value = connectionChannelBean.getValue();
        
        if (type != null && value != null) {
            switch (type) {
                case EMAIL:
                    return EMAIL_PATTERN.matcher(value).matches();
                case PHONE:
                    return PHONE_PATTERN.matcher(value).matches();
                default:
                    return true;
            }
        }
        return false;
    }
}
