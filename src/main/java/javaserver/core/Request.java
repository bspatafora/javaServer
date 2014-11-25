package javaserver.core;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String method;
    private String route;
    private String protocolVersion;
    private final List<String> headers = new ArrayList<>();
    private int contentLength = 0;
    private int rangeStart = 0;
    private int rangeEnd = 0;
    private String credentials = "";
    private String body;

    public void setMethod(String method) {
        this.method = method;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    public void addHeader(String header) {
        this.headers().add(header);
    }
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    public void setRangeStart(int rangeStart) {
        this.rangeStart = rangeStart;
    }
    public void setRangeEnd(int rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
    public void setCredentials(String credentials) {
        this.credentials = credentials;
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
    public int rangeStart() {
        return rangeStart;
    }
    public int rangeEnd() {
        return rangeEnd;
    }
    public String credentials() {
        return credentials;
    }
    public String body() {
        return body;
    }

    public Boolean hasHeader(String headerSignature) {
        return !getHeader(headerSignature).isEmpty();
    }

    public String getHeader(String headerSignature) {
        String requestedHeader = "";
        for (String header : headers) {
            if (header.contains(headerSignature)) {
                requestedHeader = header;
            }
        }
        return requestedHeader;
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
