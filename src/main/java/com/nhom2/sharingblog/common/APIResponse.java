package com.nhom2.sharingblog.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class APIResponse {
    private Integer status = 200;
    private Object data;
    private Object error;

    public APIResponse(Object data) {
        this.data = data;
    }

    public APIResponse(HttpStatus status, Object data) {
        this.status = status.value();
        this.data = data;
    }

    public APIResponse(HttpStatus status , Object data, Object error) {
        this.status = status.value();
        this.data = data;
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
