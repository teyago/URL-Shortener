package com.goncharov.tinyurl.exception;

/**
 * @author Goncharov Aleksandr
 */
public class UrlNotCreatedException extends RuntimeException {

    public UrlNotCreatedException(String msg) {
        super(msg);
    }

    public UrlNotCreatedException() {

    }
}
