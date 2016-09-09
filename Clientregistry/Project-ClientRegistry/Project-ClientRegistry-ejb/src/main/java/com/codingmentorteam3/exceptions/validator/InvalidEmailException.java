package com.codingmentorteam3.exceptions.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zsolt
 */
public class InvalidEmailException extends ValidatorException {

    public InvalidEmailException(FacesMessage message) {
        super(message);
    }

}
