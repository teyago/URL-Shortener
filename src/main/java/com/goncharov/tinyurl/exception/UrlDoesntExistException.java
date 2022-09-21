package com.goncharov.tinyurl.exception;

/**
 * @author Goncharov Aleksandr
 */
public class UrlDoesntExistException extends RuntimeException {

    public UrlDoesntExistException(String msg) {
        super(msg);
    }

    public UrlDoesntExistException() {

    }
}
