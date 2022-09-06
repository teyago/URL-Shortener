package com.goncharov.tinyurl.exception;

public class UrlDoesntExistException extends RuntimeException {

    public UrlDoesntExistException(String msg) {
        super(msg);
    }

    public UrlDoesntExistException() {

    }
}
