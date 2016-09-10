package com.codingmentorteam3.exceptions.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zsolt
 */
public class NotSamePasswordException extends ValidatorException {

    public NotSamePasswordException(FacesMessage message) {
        super(message);
    }

}
