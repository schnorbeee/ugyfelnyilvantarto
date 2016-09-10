package com.codingmentorteam3.exceptions.query;

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
