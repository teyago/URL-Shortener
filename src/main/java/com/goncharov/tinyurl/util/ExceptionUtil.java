package com.goncharov.tinyurl.util;

import com.goncharov.tinyurl.exception.UrlNotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExceptionUtil {
    public static void returnExceptionsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append("Exception in field ")
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append("; \n");
        }

        throw new UrlNotCreatedException(errorMsg.toString());
    }
}
