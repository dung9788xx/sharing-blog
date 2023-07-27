package com.nhom2.sharingblog.exceptions;

import com.nhom2.sharingblog.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public APIResponse handleUnwantedException(Exception e) {
        e.printStackTrace();
        return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse handleBindException(BindException e) {
        String errorMessage = "Bad request";
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            errorMessage = fieldError.getField()+ " " + fieldError.getDefaultMessage();
        }
        return new APIResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
