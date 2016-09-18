package com.codingmentorteam3.exceptions.query;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException() {
        //Default constructor
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
