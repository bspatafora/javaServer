package com.bspatafora.cobspec.handlers;

import com.bspatafora.javaserver.Handler;
import com.bspatafora.javaserver.constants.Headers;
import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;

public class Redirect implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(StatusLine.MOVED_PERMANENTLY);
        response.addHeader(Headers.LOCATION + "/");
        return response;
    }
}
