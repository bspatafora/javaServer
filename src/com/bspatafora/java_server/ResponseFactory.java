package com.bspatafora.java_server;

import java.util.ArrayList;
import java.util.List;

public class ResponseFactory {
    private Request request;
    private Response response;
    private List<String> headers;

    public ResponseFactory(Request request) {
        this.request = request;
        this.response = new Response();
        this.headers = new ArrayList<>();
    }

    public Response build() {
        response.setProtocolVersion(request.protocolVersion());
        response.setStatus(status());
        response.setHeaders(headers());
        response.setBody(body());
        return response;
    }

    private String status() {
        if (request.route().equals(Routes.ROOT) || request.route().equals(Routes.FORM)) {
            return StatusLine.OK;
        } else if (request.route().equals(Routes.REDIRECT)) {
            return StatusLine.MOVED_PERMANENTLY;
        } else {
            return StatusLine.NOT_FOUND;
        }
    }

    private List<String> headers() {
        if (request.route().equals(Routes.REDIRECT)) {
            headers.add(Headers.LOCATION + "http://localhost:5000" + Routes.ROOT);
        }
        return headers;
    }

    private String body() {
        if (request.route().equals(Routes.FORM) && request.method().equals(Methods.GET)) {
            return Resources.form_resource;
        } else if (request.route().equals(Routes.ROOT)) {
            return "Hello, world!";
        } else if (request.route().equals(Routes.REDIRECT)) {
            return "Moved.";
        } else {
            return "Not found.";
        }
    }
}
