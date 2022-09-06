package com.goncharov.tinyurl.exception;

public class UrlNotCreatedException extends RuntimeException {

    public UrlNotCreatedException(String msg) {
        super(msg);
    }

    public UrlNotCreatedException() {

    }
}
