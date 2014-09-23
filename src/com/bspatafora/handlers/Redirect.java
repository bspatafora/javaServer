package com.bspatafora.handlers;

import com.bspatafora.constants.Headers;
import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.Response;
import com.bspatafora.constants.StatusLine;

public class Redirect {
    public Response response(Request request) {
        Response response = new Response();
        response.setProtocolVersion(StatusLine.HTTP11);
        response.setStatus(StatusLine.MOVED_PERMANENTLY);
        response.addHeader(Headers.LOCATION + "/");
        return response;
    }
}
