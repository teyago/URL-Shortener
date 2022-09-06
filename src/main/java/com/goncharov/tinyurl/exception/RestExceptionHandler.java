package com.goncharov.tinyurl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(UrlDoesntExistException.class)
    private ResponseEntity<Object> handleDoesntExist(UrlDoesntExistException e) {
        return new ResponseEntity<>("Url doesn't exist or has been expired", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UrlNotCreatedException.class)
    protected ResponseEntity<Object> handleNotCreated(UrlNotCreatedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
