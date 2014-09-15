package com.bspatafora.java_server;

import java.util.List;

public class Request {
    private String method;
    private String route;
    private int contentLength;
    private String body;
    private List<String> headers;

    public Request() {
        this.contentLength = 0;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public String method() {
        return method;
    }
    public String route() {
        return route;
    }
    public int contentLength() {
        return contentLength;
    }
    public String body() {
        return body;
    }
    public List<String> headers() {
        return headers;
    }
}