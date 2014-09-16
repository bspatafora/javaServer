package com.bspatafora.java_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RequestFactory {
    private BufferedReader in;
    private List<String> headers = new ArrayList<>();
    private Request request;

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
            StringTokenizer requestLineElements = new StringTokenizer(requestLine);
            request.setMethod(requestLineElements.nextToken());
            request.setRoute(requestLineElements.nextToken());
            request.setProtocolVersion(requestLineElements.nextToken());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    private void parseHeaders() {
        try {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                if (currentLine.length() == 0)
                    break;
                headers.add(currentLine);
                if (contentLengthHeader(currentLine)) {
                    request.setContentLength(parseContentLength(currentLine));
                }
            }
            request.setHeaders(headers);
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
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
                System.err.println("Caught IOException: " + e.getMessage());
            }
        }
    }

    private Boolean requestHasBody() {
        return request.method().equals(Methods.POST) || request.method().equals(Methods.PUT);
    }

    private int parseContentLength(String header) {
        return Integer.parseInt((header.substring(Headers.CONTENT_LENGTH.length())));
    }

    private boolean contentLengthHeader(String header) {
        return header.startsWith(Headers.CONTENT_LENGTH);
    }
}