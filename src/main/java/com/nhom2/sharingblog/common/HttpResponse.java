package com.nhom2.sharingblog.common;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;

public class HttpResponse {
    private HttpStatus status = HttpStatus.valueOf(200);
    private JSONObject json;

    public HttpResponse(HttpStatus status, String html) {
        this.status = status;
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    private String html;

    public HttpResponse(JSONObject json) {
        this.json = json;
    }

    public HttpResponse(HttpStatus status, JSONObject json) {
        this.status = status;
        this.json = json;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
}
