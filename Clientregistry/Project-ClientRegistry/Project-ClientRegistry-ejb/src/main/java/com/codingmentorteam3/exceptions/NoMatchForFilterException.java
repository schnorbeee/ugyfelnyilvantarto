package com.codingmentorteam3.exceptions;

/**
 *
 * @author Zsolt
 */
public class NoMatchForFilterException extends QueryException {

    public NoMatchForFilterException() {
//        constructor with no arguments
    }

    public NoMatchForFilterException(String message) {
        super(message);
    }

}
