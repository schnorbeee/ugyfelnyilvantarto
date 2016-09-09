package com.codingmentorteam3.exceptions.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zsolt
 */
public class InvalidUsernameException extends ValidatorException {

    public InvalidUsernameException(FacesMessage message) {
        super(message);
    }

}
