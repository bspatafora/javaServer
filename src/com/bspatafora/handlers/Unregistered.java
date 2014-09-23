package com.bspatafora.handlers;

import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.Response;
import com.bspatafora.constants.StatusLine;

public class Unregistered {
    public Response response(Request request) {
        Response response = new Response();
        response.setProtocolVersion(StatusLine.HTTP11);
        response.setStatus(StatusLine.NOT_FOUND);
        response.setBody("Not found.");
        return response;
    }
}
