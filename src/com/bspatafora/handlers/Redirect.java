package com.bspatafora.handlers;

import com.bspatafora.javaserver.constants.Headers;
import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;

public class Redirect {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(StatusLine.MOVED_PERMANENTLY);
        response.addHeader(Headers.LOCATION + "/");
        return response;
    }
}
