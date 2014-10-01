package com.bspatafora.core;

import com.bspatafora.core.constants.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

class RequestFactory {
    private final BufferedReader in;
    private final Request request;

    public RequestFactory(BufferedReader in) {
        this.in = in;
        this.request = new Request();
    }

    public Request build() {
        parseRequestLine();
        parseHeaders();
        parseBody();
        return request;
    }

    private void parseRequestLine() {
        try {
            String requestLine = in.readLine();
            while (requestLine == null) requestLine = in.readLine();
            StringTokenizer requestLineElements = new StringTokenizer(requestLine);
            request.setMethod(requestLineElements.nextToken());
            request.setRoute(requestLineElements.nextToken());
            request.setProtocolVersion(requestLineElements.nextToken());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void parseHeaders() {
        try {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                if (currentLine.length() == 0)
                    break;
                request.addHeader(currentLine);
                if (contentLengthHeader(currentLine)) {
                    request.setContentLength(parseContentLength(currentLine));
                } else if (basicAuthHeader(currentLine)) {
                    request.setCredentials(parseCredentials(currentLine));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void parseBody() {
        if (requestHasBody()) {
            try {
                StringBuilder body = new StringBuilder();
                for (int i = 0; i < request.contentLength(); i++) {
                    body.append((char) in.read());
                }
                request.setBody(body.toString());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private Boolean requestHasBody() {
        return request.contentLength() > 0;
    }

    private int parseContentLength(String header) {
        return Integer.parseInt((header.substring(Header.CONTENT_LENGTH.length())));
    }

    private String parseCredentials(String header) {
        return header.substring(Header.BASIC_AUTH.length());
    }

    private boolean contentLengthHeader(String header) {
        return header.startsWith(Header.CONTENT_LENGTH);
    }

    private boolean basicAuthHeader(String header) {
        return header.startsWith(Header.BASIC_AUTH);
    }
}
