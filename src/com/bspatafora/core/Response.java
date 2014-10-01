package com.bspatafora.core;

import com.bspatafora.core.constants.Status;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private final String protocolVersion = Status.HTTP11;
    private String status;
    private final List<String> headers = new ArrayList<>();
    private byte[] body = new byte[0];

    public void setStatus(String status) {
        this.status = status;
    }
    public void addHeader(String header) {
        this.headers.add(header);
    }
    public void setBody(byte[] body) {
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
    public byte[] body() {
        return body;
    }

    public String head() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(protocolVersion);
        stringBuilder.append(" ");
        stringBuilder.append(status);
        for (String header : headers) {
            stringBuilder.append(header);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }
}
