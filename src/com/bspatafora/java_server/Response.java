package com.bspatafora.java_server;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String protocolVersion;
    private String status;
    private List<String> headers = new ArrayList<>();
    private String body;

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
    public void addHeader(String header) {
        this.headers.add(header);
    } // TEST THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void setBody(String body) {
        this.body = body;
    }

    public String protocolVersion() {
        return protocolVersion;
    }
    public String status() {
        return status;
    }
    public List<String> headers() {
        return headers;
    }
    public String body() {
        return body;
    }

    public String responseString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(protocolVersion);
        stringBuilder.append(" ");
        stringBuilder.append(status);
        for (String header : headers) {
            stringBuilder.append(header);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        stringBuilder.append(body);
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }
}
