package com.bspatafora.handlers;

import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;

public class Unregistered {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(StatusLine.NOT_FOUND);
        response.setBody("Not found.");
        return response;
    }
}
