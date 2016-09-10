package com.codingmentorteam3.exceptions.query;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class LastAdminException extends RuntimeException { 

    public LastAdminException() {
        //Default contructor
    }

    public LastAdminException(String message) {
        super(message);
    }

}
