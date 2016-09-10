package com.codingmentorteam3.exceptions.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zsolt
 */
public class InvalidPasswordException extends ValidatorException{

    public InvalidPasswordException(FacesMessage message) {
        super(message);
    }

}
