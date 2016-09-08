package com.codingmentorteam3.exceptions;

/**
 *
 * @author Zsolt
 */
public class QueryException extends RuntimeException{

    public QueryException() {
//        constructor with no arguments
    }

    public QueryException(String message) {
        super(message);
    }

}
