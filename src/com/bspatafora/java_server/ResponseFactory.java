package com.bspatafora.java_server;

public class ResponseFactory {
    private Request request;
    private Response response;

    public ResponseFactory(Request request) {
        this.request = request;
        this.response = new Response();
    }

    public Response build() {
        response.setProtocolVersion(request.protocolVersion());
        response.setStatus(status());
        response.setBody(body());
        return response;
    }

    private String status() {
        if (request.route().equals(Routes.ROOT) || request.route().equals(Routes.FORM)) {
            return StatusLine.OK;
        } else {
            return StatusLine.NOT_FOUND;
        }
    }

    private String body() {
        if (request.route().equals(Routes.FORM) && request.method().equals(Methods.GET)) {
            return Resources.form_resource;
        } else if (request.route().equals(Routes.ROOT)) {
            return "Hello, world!";
        } else {
            return "Not found.";
        }
    }
}
