package com.nhom2.sharingblog.exceptions;

import com.nhom2.sharingblog.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public APIResponse handleUnwantedException(Exception e) {
        e.printStackTrace();
        return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
