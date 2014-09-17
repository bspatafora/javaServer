package com.bspatafora.java_server;

import java.util.List;

public class Request {
    private String method;
    private String route;
    private String protocolVersion;
    private List<String> headers;
    private int contentLength;
    private String body;

    public Request() {
        this.contentLength = 0;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String method() {
        return method;
    }
    public String route() {
        return route;
    }
    public String protocolVersion() {
        return protocolVersion;
    }
    public List<String> headers() {
        return headers;
    }
    public int contentLength() {
        return contentLength;
    }
    public String body() {
        return body;
    }

    public String requestString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method);
        stringBuilder.append(" ");
        stringBuilder.append(route);
        stringBuilder.append(" ");
        stringBuilder.append(protocolVersion);
        stringBuilder.append("\r\n");
        for (String header : headers) {
            stringBuilder.append(header);
            stringBuilder.append("\r\n");
        }
        if (body != null) {
            stringBuilder.append("\r\n");
            stringBuilder.append(body);
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }
}
