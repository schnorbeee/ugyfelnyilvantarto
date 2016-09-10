package com.codingmentorteam3.exceptions.query;

/**
 *
 * @author Zsolt
 */
public class BadRequestException extends QueryException {

    public BadRequestException() {
//        constructor with no arguments
    }

    public BadRequestException(String message) {
        super(message);
    }

}
