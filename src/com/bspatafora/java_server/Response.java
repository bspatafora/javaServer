package com.bspatafora.java_server;

public class Response {
    private String protocolVersion;
    private String status;
    private String body;

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String protocolVersion() {
        return protocolVersion;
    }
    public String status() {
        return status;
    }
    public String body() {
        return body;
    }

    public String responseString() {
        return protocolVersion + " " + status + "\r\n" + body + "\r\n";
    }
}
