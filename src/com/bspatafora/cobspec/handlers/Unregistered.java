package com.bspatafora.cobspec.handlers;

import com.bspatafora.javaserver.Handler;
import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;

public class Unregistered implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(StatusLine.NOT_FOUND);
        response.setBody("Not found.");
        return response;
    }
}
