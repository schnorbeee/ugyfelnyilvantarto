package com.codingmentorteam3.exceptions.query;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class OldPasswordException extends RuntimeException {

    public OldPasswordException() {
        //Default contructor
    }

    public OldPasswordException(String message) {
        super(message);
    }

}
